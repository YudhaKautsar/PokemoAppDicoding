package com.yudha.pokemoapp.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    @SerializedName("results")
    val results: List<PokemonItemResponse>
)

data class PokemonItemResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
