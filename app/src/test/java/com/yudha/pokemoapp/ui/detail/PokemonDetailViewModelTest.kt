package com.yudha.pokemoapp.ui.detail

import com.yudha.pokemoapp.MainDispatcherRule
import com.yudha.pokemoapp.core.domain.model.PokemonDetail
import com.yudha.pokemoapp.core.domain.model.resource.Resource
import com.yudha.pokemoapp.core.domain.usecase.GetFavoriteStatusUseCase
import com.yudha.pokemoapp.core.domain.usecase.GetPokemonDetailUseCase
import com.yudha.pokemoapp.core.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class PokemonDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var getPokemonDetailUseCase: GetPokemonDetailUseCase
    @Mock
    private lateinit var getFavoriteStatusUseCase: GetFavoriteStatusUseCase
    @Mock
    private lateinit var toggleFavoriteUseCase: ToggleFavoriteUseCase

    private lateinit var viewModel: PokemonDetailViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = PokemonDetailViewModel(getPokemonDetailUseCase, getFavoriteStatusUseCase, toggleFavoriteUseCase)
    }

    @Test
    fun `when fetchPokemonDetail succeeds, pokemonDetail should be updated`() = runTest {
        // Given
        val pokemonName = "Bulbasaur"
        val pokemonDetail = PokemonDetail(1, pokemonName, 7, 69, "image", listOf("grass"), "description")
        `when`(getPokemonDetailUseCase(pokemonName)).thenReturn(flowOf(Resource.Success(pokemonDetail)))
        `when`(getFavoriteStatusUseCase(pokemonName)).thenReturn(flowOf(true))

        // When
        viewModel.fetchPokemonDetail(pokemonName)

        // Then
        assertEquals(pokemonDetail, viewModel.pokemonDetail.value)
    }
}
