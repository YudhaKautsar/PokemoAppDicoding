package com.yudha.pokemoapp.core.data.repository

import com.yudha.pokemoapp.core.data.local.dao.PokemonDao
import com.yudha.pokemoapp.core.data.mapper.PokemonMapper
import com.yudha.pokemoapp.core.data.remote.ApiService
import com.yudha.pokemoapp.core.domain.model.Pokemon
import com.yudha.pokemoapp.core.domain.model.PokemonDetail
import com.yudha.pokemoapp.core.domain.model.resource.Resource
import com.yudha.pokemoapp.core.domain.repository.PokemonRepository
import com.yudha.pokemoapp.core.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class PokemonRepositoryImpl(
    private val apiService: ApiService,
    private val pokemonDao: PokemonDao
) : PokemonRepository {

    override fun getPokemonList(limit: Int, offset: Int): Flow<Resource<List<Pokemon>>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.getPokemonList(limit, offset)
            val list = response.results.map { PokemonMapper.mapItemResponseToDomain(it) }
            emit(Resource.Success(list))
        } catch (e: Exception) {
            val localFavorites = pokemonDao.getAllFavorites().map { entities ->
                entities.map { PokemonMapper.mapEntityToDomain(it) }
            }.first()
            
            if (localFavorites.isNotEmpty()) {
                emit(Resource.Success(localFavorites))
            } else {
                emit(Resource.Error(e.message ?: Constants.ERROR_UNKNOWN))
            }
        }
    }

    override fun getPokemonDetail(name: String): Flow<Resource<PokemonDetail>> = flow {
        emit(Resource.Loading())
        try {
            val detail = apiService.getPokemonDetail(name)
            val species = try {
                apiService.getPokemonSpecies(name)
            } catch (_: Exception) {
                null
            }
            val domainDetail = PokemonMapper.mapDetailResponseToDomain(detail, species)
            emit(Resource.Success(domainDetail))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown Error"))
        }
    }

    override fun getFavoritePokemon(): Flow<Resource<List<Pokemon>>> = flow {
        emit(Resource.Loading())
        try {
            pokemonDao.getAllFavorites().map { entities ->
                entities.map { PokemonMapper.mapEntityToDomain(it) }
            }.collect { list ->
                emit(Resource.Success(list))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown Error"))
        }
    }

    override fun isFavorite(name: String): Flow<Boolean> {
        return pokemonDao.isFavorite(name)
    }

    override suspend fun setFavorite(pokemon: Pokemon, isFavorite: Boolean) {
        val entity = PokemonMapper.mapDomainToEntity(pokemon)
        if (isFavorite) {
            pokemonDao.insertFavorite(entity)
        } else {
            pokemonDao.deleteFavorite(entity)
        }
    }
}
