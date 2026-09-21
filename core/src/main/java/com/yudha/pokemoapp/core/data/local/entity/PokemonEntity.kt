package com.yudha.pokemoapp.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_pokemon")
data class PokemonEntity(
    @PrimaryKey
    val name: String,
    val url: String,
    val imageUrl: String
)
