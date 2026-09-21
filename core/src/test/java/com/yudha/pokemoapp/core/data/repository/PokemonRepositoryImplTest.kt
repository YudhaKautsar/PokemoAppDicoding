package com.yudha.pokemoapp.core.data.repository

import com.yudha.pokemoapp.core.data.local.dao.PokemonDao
import com.yudha.pokemoapp.core.data.remote.ApiService
import com.yudha.pokemoapp.core.data.remote.response.PokemonItemResponse
import com.yudha.pokemoapp.core.data.remote.response.PokemonListResponse
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class PokemonRepositoryImplTest {

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var pokemonDao: PokemonDao

    private lateinit var repository: PokemonRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = PokemonRepositoryImpl(apiService, pokemonDao)
    }

    @Test
    fun `getPokemonList should return list of pokemon from api`() = runTest {
        // Given
        val response = PokemonListResponse(
            results = listOf(
                PokemonItemResponse("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/")
            )
        )
        whenever(apiService.getPokemonList(100, 0)).thenReturn(response)

        // When
        val result = repository.getPokemonList(100, 0)

        // Then
        assertEquals(1, result.size)
        assertEquals("bulbasaur", result[0].name)
        assertEquals("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png", result[0].imageUrl)
    }
}
