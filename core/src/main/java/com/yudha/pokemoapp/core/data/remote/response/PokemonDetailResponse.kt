package com.yudha.pokemoapp.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("sprites")
    val sprites: SpritesResponse,
    @SerializedName("types")
    val types: List<TypeSlotResponse>
)

data class SpritesResponse(
    @SerializedName("front_default")
    val frontDefault: String?
)

data class TypeSlotResponse(
    @SerializedName("type")
    val type: TypeResponse
)

data class TypeResponse(
    @SerializedName("name")
    val name: String
)
