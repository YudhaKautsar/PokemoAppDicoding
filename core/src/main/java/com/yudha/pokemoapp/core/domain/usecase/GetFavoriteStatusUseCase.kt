package com.yudha.pokemoapp.core.domain.usecase

import com.yudha.pokemoapp.core.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteStatusUseCase(private val repository: PokemonRepository) {
    operator fun invoke(name: String): Flow<Boolean> {
        return repository.isFavorite(name)
    }
}
