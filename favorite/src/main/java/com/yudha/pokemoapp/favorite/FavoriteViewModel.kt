package com.yudha.pokemoapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yudha.pokemoapp.core.domain.model.Pokemon
import com.yudha.pokemoapp.core.domain.model.resource.Resource
import com.yudha.pokemoapp.core.domain.usecase.GetFavoritePokemonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FavoriteViewModel(
    private val getFavoritePokemonUseCase: GetFavoritePokemonUseCase
) : ViewModel() {

    private val _favoritePokemon = MutableStateFlow<List<Pokemon>>(emptyList())
    val favoritePokemon: StateFlow<List<Pokemon>> = _favoritePokemon

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        observeFavorites()
    }

    private fun observeFavorites() {
        getFavoritePokemonUseCase().onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _isLoading.value = true
                    _error.value = null
                }
                is Resource.Success -> {
                    _isLoading.value = false
                    _favoritePokemon.value = resource.data.orEmpty()
                }
                is Resource.Error -> {
                    _isLoading.value = false
                    _error.value = resource.message
                }
            }
        }.launchIn(viewModelScope)
    }
}
