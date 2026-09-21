package com.yudha.pokemoapp.core.domain.usecase

import com.yudha.pokemoapp.core.domain.model.Pokemon
import com.yudha.pokemoapp.core.domain.model.resource.Resource
import com.yudha.pokemoapp.core.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonListUseCase(private val repository: PokemonRepository) {
    operator fun invoke(limit: Int, offset: Int): Flow<Resource<List<Pokemon>>> {
        return repository.getPokemonList(limit, offset)
    }
}
