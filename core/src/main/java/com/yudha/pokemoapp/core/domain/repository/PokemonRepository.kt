package com.yudha.pokemoapp.core.domain.repository

import com.yudha.pokemoapp.core.domain.model.Pokemon
import com.yudha.pokemoapp.core.domain.model.PokemonDetail
import com.yudha.pokemoapp.core.domain.model.resource.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(limit: Int, offset: Int): Flow<Resource<List<Pokemon>>>
    fun getPokemonDetail(name: String): Flow<Resource<PokemonDetail>>
    
    fun getFavoritePokemon(): Flow<Resource<List<Pokemon>>>
    fun isFavorite(name: String): Flow<Boolean>
    suspend fun setFavorite(pokemon: Pokemon, isFavorite: Boolean)
}
