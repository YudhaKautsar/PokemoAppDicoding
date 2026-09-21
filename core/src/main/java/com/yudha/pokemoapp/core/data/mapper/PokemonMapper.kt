package com.yudha.pokemoapp.core.data.mapper

import com.yudha.pokemoapp.core.data.local.entity.PokemonEntity
import com.yudha.pokemoapp.core.data.remote.response.PokemonDetailResponse
import com.yudha.pokemoapp.core.data.remote.response.PokemonItemResponse
import com.yudha.pokemoapp.core.data.remote.response.PokemonSpeciesResponse
import com.yudha.pokemoapp.core.domain.model.Pokemon
import com.yudha.pokemoapp.core.domain.model.PokemonDetail

object PokemonMapper {

    fun mapItemResponseToDomain(response: PokemonItemResponse): Pokemon {
        val id = response.url.split("/").asSequence().filter { it.isNotEmpty() }.last()
        return Pokemon(
            name = response.name,
            url = response.url,
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
        )
    }

    fun mapDetailResponseToDomain(
        detail: PokemonDetailResponse,
        species: PokemonSpeciesResponse?
    ): PokemonDetail {
        val description = species?.flavorTextEntries
            ?.find { it.language.name == "en" }
            ?.flavorText
            ?.replace("\n", " ")
            ?.replace("\u000c", " ") ?: "No description available"

        return PokemonDetail(
            id = detail.id,
            name = detail.name,
            height = detail.height,
            weight = detail.weight,
            imageUrl = detail.sprites.frontDefault,
            types = detail.types.map { it.type.name },
            description = description
        )
    }

    fun mapEntityToDomain(entity: PokemonEntity): Pokemon {
        return Pokemon(
            name = entity.name,
            url = entity.url,
            imageUrl = entity.imageUrl
        )
    }

    fun mapDomainToEntity(pokemon: Pokemon): PokemonEntity {
        return PokemonEntity(
            name = pokemon.name,
            url = pokemon.url,
            imageUrl = pokemon.imageUrl
        )
    }
}
