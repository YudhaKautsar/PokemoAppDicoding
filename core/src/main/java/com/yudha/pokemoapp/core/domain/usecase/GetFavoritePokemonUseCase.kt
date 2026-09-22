package com.yudha.pokemoapp.core.domain.usecase

import com.yudha.pokemoapp.core.domain.model.Pokemon
import com.yudha.pokemoapp.core.domain.model.resource.Resource
import com.yudha.pokemoapp.core.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class GetFavoritePokemonUseCase(private val repository: PokemonRepository) {
    operator fun invoke(): Flow<Resource<List<Pokemon>>> {
        return repository.getFavoritePokemon()
    }
}
