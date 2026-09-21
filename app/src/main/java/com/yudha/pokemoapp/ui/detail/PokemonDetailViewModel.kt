package com.yudha.pokemoapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yudha.pokemoapp.core.domain.model.Pokemon
import com.yudha.pokemoapp.core.domain.model.PokemonDetail
import com.yudha.pokemoapp.core.domain.model.resource.Resource
import com.yudha.pokemoapp.core.domain.usecase.GetFavoriteStatusUseCase
import com.yudha.pokemoapp.core.domain.usecase.GetPokemonDetailUseCase
import com.yudha.pokemoapp.core.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val getFavoriteStatusUseCase: GetFavoriteStatusUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _pokemonDetail = MutableStateFlow<PokemonDetail?>(null)
    val pokemonDetail: StateFlow<PokemonDetail?> = _pokemonDetail

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    fun fetchPokemonDetail(name: String) {
        getPokemonDetailUseCase(name).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _isLoading.value = true
                    _error.value = null
                    _pokemonDetail.value = null
                }
                is Resource.Success -> {
                    _isLoading.value = false
                    _pokemonDetail.value = resource.data
                }
                is Resource.Error -> {
                    _isLoading.value = false
                    _error.value = resource.message
                }
            }
        }.launchIn(viewModelScope)
        
        viewModelScope.launch {
            getFavoriteStatusUseCase(name).collectLatest {
                _isFavorite.value = it
            }
        }
    }

    fun toggleFavorite(pokemon: Pokemon) {
        viewModelScope.launch {
            toggleFavoriteUseCase(pokemon, !_isFavorite.value)
        }
    }
}
