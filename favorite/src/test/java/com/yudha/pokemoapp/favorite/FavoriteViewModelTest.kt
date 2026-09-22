package com.yudha.pokemoapp.favorite

import com.yudha.pokemoapp.core.domain.model.Pokemon
import com.yudha.pokemoapp.core.domain.model.resource.Resource
import com.yudha.pokemoapp.core.domain.usecase.GetFavoritePokemonUseCase
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
class FavoriteViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var getFavoritePokemonUseCase: GetFavoritePokemonUseCase

    private lateinit var viewModel: FavoriteViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `when favorites are updated, favoritePokemon should be updated`() = runTest {
        // Given
        val favorites = listOf(Pokemon("Pikachu", "url", "image"))
        `when`(getFavoritePokemonUseCase()).thenReturn(flowOf(Resource.Success(favorites)))

        // When
        viewModel = FavoriteViewModel(getFavoritePokemonUseCase)

        // Then
        assertEquals(favorites, viewModel.favoritePokemon.value)
        assertEquals(false, viewModel.isLoading.value)
    }
}
