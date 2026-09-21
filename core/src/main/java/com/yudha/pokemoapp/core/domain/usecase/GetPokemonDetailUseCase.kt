package com.yudha.pokemoapp.core.domain.usecase

import com.yudha.pokemoapp.core.domain.model.PokemonDetail
import com.yudha.pokemoapp.core.domain.model.resource.Resource
import com.yudha.pokemoapp.core.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonDetailUseCase(private val repository: PokemonRepository) {
    operator fun invoke(name: String): Flow<Resource<PokemonDetail>> {
        return repository.getPokemonDetail(name)
    }
}
