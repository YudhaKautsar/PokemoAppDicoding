package com.yudha.pokemoapp.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class PokemonSpeciesResponse(
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntryResponse>
)

data class FlavorTextEntryResponse(
    @SerializedName("flavor_text")
    val flavorText: String,
    @SerializedName("language")
    val language: LanguageResponse
)

data class LanguageResponse(
    @SerializedName("name")
    val name: String
)
