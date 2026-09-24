package com.yudha.pokemoapp.core.domain.model

data class Pokemon(
    val name: String,
    val url: String,
    val imageUrl: String
) {
    val id: String
        get() = url.split("/").last { it.isNotEmpty() }
}
