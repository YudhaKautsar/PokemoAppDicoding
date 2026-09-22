package com.yudha.pokemoapp.core.data.repository

import com.yudha.pokemoapp.core.data.local.dao.PokemonDao
import com.yudha.pokemoapp.core.data.local.entity.PokemonEntity
import com.yudha.pokemoapp.core.data.remote.ApiService
import com.yudha.pokemoapp.core.data.remote.response.PokemonItemResponse
import com.yudha.pokemoapp.core.data.remote.response.PokemonListResponse
import com.yudha.pokemoapp.core.domain.model.resource.Resource
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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
        val results = repository.getPokemonList(100, 0).toList()

        // Then
        assertTrue(results[0] is Resource.Loading)
        assertTrue(results[1] is Resource.Success)
        val successData = (results[1] as Resource.Success).data
        assertEquals(1, successData?.size)
        assertEquals("bulbasaur", successData?.get(0)?.name)
    }

    @Test
    fun `getPokemonList should return favorites from database when api fails`() = runTest {
        // Given
        whenever(apiService.getPokemonList(100, 0)).thenThrow(RuntimeException("Network Error"))
        val localFavorites = listOf(PokemonEntity("Pikachu", "url", "image"))
        whenever(pokemonDao.getAllFavorites()).thenReturn(flowOf(localFavorites))

        // When
        val results = repository.getPokemonList(100, 0).toList()

        // Then
        assertTrue(results[0] is Resource.Loading)
        assertTrue(results[1] is Resource.Success)
        val successData = (results[1] as Resource.Success).data
        assertEquals(1, successData?.size)
        assertEquals("Pikachu", successData?.get(0)?.name)
    }
}
