package com.yudha.pokemoapp.core.data.remote

import com.yudha.pokemoapp.core.data.remote.response.PokemonDetailResponse
import com.yudha.pokemoapp.core.data.remote.response.PokemonListResponse
import com.yudha.pokemoapp.core.data.remote.response.PokemonSpeciesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): PokemonListResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): PokemonDetailResponse

    @GET("pokemon-species/{name}")
    suspend fun getPokemonSpecies(
        @Path("name") name: String
    ): PokemonSpeciesResponse
}
