package com.yudha.pokemoapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yudha.pokemoapp.core.domain.model.Pokemon
import com.yudha.pokemoapp.core.domain.usecase.GetFavoritePokemonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val getFavoritePokemonUseCase: GetFavoritePokemonUseCase
) : ViewModel() {

    private val _favoritePokemon = MutableStateFlow<List<Pokemon>>(emptyList())
    val favoritePokemon: StateFlow<List<Pokemon>> = _favoritePokemon

    init {
        observeFavorites()
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            getFavoritePokemonUseCase().collect {
                _favoritePokemon.value = it
            }
        }
    }
}
