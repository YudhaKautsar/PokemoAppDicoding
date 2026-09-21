package com.yudha.pokemoapp.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yudha.pokemoapp.core.data.local.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(pokemon: PokemonEntity)

    @Delete
    suspend fun deleteFavorite(pokemon: PokemonEntity)

    @Query("SELECT * FROM favorite_pokemon")
    fun getAllFavorites(): Flow<List<PokemonEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_pokemon WHERE name = :name)")
    fun isFavorite(name: String): Flow<Boolean>
}
