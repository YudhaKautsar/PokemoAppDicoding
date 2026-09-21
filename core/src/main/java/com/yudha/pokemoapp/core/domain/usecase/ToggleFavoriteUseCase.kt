package com.yudha.pokemoapp.core.domain.usecase

import com.yudha.pokemoapp.core.domain.model.Pokemon
import com.yudha.pokemoapp.core.domain.repository.PokemonRepository

class ToggleFavoriteUseCase(private val repository: PokemonRepository) {
    suspend operator fun invoke(pokemon: Pokemon, isFavorite: Boolean) {
        repository.setFavorite(pokemon, isFavorite)
    }
}
