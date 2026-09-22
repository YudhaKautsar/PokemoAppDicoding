package com.yudha.pokemoapp.ui.list

import com.yudha.pokemoapp.MainDispatcherRule
import com.yudha.pokemoapp.core.domain.model.Pokemon
import com.yudha.pokemoapp.core.domain.model.resource.Resource
import com.yudha.pokemoapp.core.domain.usecase.GetPokemonListUseCase
import com.yudha.pokemoapp.core.domain.usecase.GetSortOrderUseCase
import com.yudha.pokemoapp.core.domain.usecase.GetSortSettingUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
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
    @Mock
    private lateinit var getSortSettingUseCase: GetSortSettingUseCase
    @Mock
    private lateinit var getSortOrderUseCase: GetSortOrderUseCase

    private lateinit var viewModel: PokemonListViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        `when`(getSortSettingUseCase()).thenReturn(flowOf("name"))
        `when`(getSortOrderUseCase()).thenReturn(flowOf(true))
    }

    @Test
    fun `when fetchPokemonList succeeds, pokemonList should be updated`() = runTest {
        // Given
        val pokemonList = listOf(
            Pokemon("Bulbasaur", "url1", "image1"),
            Pokemon("Ivysaur", "url2", "image2")
        )
        `when`(getPokemonListUseCase(100, 0)).thenReturn(flowOf(Resource.Success(pokemonList)))

        // When
        viewModel = PokemonListViewModel(getPokemonListUseCase, getSortSettingUseCase, getSortOrderUseCase)
        val job = viewModel.pokemonList.launchIn(this)

        // Then
        assertEquals(pokemonList, viewModel.pokemonList.value)
        assertEquals(false, viewModel.isLoading.value)
        
        job.cancel()
    }
}
