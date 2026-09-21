package com.yudha.pokemoapp.ui.list

import com.yudha.pokemoapp.MainDispatcherRule
import com.yudha.pokemoapp.core.domain.model.Pokemon
import com.yudha.pokemoapp.core.domain.usecase.GetPokemonListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class PokemonListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var getPokemonListUseCase: GetPokemonListUseCase

    private lateinit var viewModel: PokemonListViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `when fetchPokemonList succeeds, pokemonList should be updated`() = runTest {
        // Given
        val pokemonList = listOf(
            Pokemon("Bulbasaur", "url1", "image1"),
            Pokemon("Ivysaur", "url2", "image2")
        )
        `when`(getPokemonListUseCase(100, 0)).thenReturn(pokemonList)

        // When
        viewModel = PokemonListViewModel(getPokemonListUseCase)
        val job = viewModel.pokemonList.launchIn(this)

        // Then
        assertEquals(pokemonList, viewModel.pokemonList.value)
        assertEquals(false, viewModel.isLoading.value)
        
        job.cancel()
    }
}
