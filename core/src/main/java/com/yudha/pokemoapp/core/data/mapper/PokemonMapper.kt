package com.yudha.pokemoapp.core.data.mapper

import com.yudha.pokemoapp.core.data.local.entity.PokemonEntity
import com.yudha.pokemoapp.core.data.remote.response.PokemonDetailResponse
import com.yudha.pokemoapp.core.data.remote.response.PokemonItemResponse
import com.yudha.pokemoapp.core.data.remote.response.PokemonSpeciesResponse
import com.yudha.pokemoapp.core.domain.model.Pokemon
import com.yudha.pokemoapp.core.domain.model.PokemonDetail
import com.yudha.pokemoapp.core.utils.Constants

object PokemonMapper {

    fun mapItemResponseToDomain(response: PokemonItemResponse): Pokemon {
        val id = response.url.split("/").asSequence().last { it.isNotEmpty() }
        return Pokemon(
            name = response.name,
            url = response.url,
            imageUrl = Constants.POKEMON_IMAGE_URL_FORMAT.format(id)
        )
    }

    fun mapDetailResponseToDomain(
        detail: PokemonDetailResponse,
        species: PokemonSpeciesResponse?
    ): PokemonDetail {
        val description = species?.flavorTextEntries
            ?.find { it.language.name == Constants.LANGUAGE_EN }
            ?.flavorText
            ?.replace("\n", " ")
            ?.replace("\u000c", " ") ?: Constants.NO_DESCRIPTION_AVAILABLE

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
