package com.yudha.pokemoapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yudha.pokemoapp.core.domain.model.Pokemon
import com.yudha.pokemoapp.core.domain.model.resource.Resource
import com.yudha.pokemoapp.core.domain.usecase.GetPokemonListUseCase
import com.yudha.pokemoapp.core.domain.usecase.GetSortOrderUseCase
import com.yudha.pokemoapp.core.domain.usecase.GetSortSettingUseCase
import com.yudha.pokemoapp.core.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn

class PokemonListViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    getSortSettingUseCase: GetSortSettingUseCase,
    getSortOrderUseCase: GetSortOrderUseCase
) : ViewModel() {

    private val _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    
    private val _searchQuery = MutableStateFlow("")

    val pokemonList: StateFlow<List<Pokemon>> = combine(
        _pokemonList, 
        _searchQuery, 
        getSortSettingUseCase(), 
        getSortOrderUseCase()
    ) { list, query, sort, isAscending ->
        val filteredList = if (query.isBlank()) {
            list
        } else {
            list.filter { it.name.contains(query, ignoreCase = true) }
        }
        
        val sortedList = when (sort) {
            Constants.SORT_BY_NAME -> filteredList.sortedBy { it.name }
            Constants.SORT_BY_ID -> filteredList.sortedBy { it.id.toIntOrNull() ?: 0 }
            else -> filteredList
        }

        if (isAscending) sortedList else sortedList.reversed()
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchPokemonList()
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun fetchPokemonList() {
        getPokemonListUseCase(100, 0).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _isLoading.value = true
                    _error.value = null
                }
                is Resource.Success -> {
                    _isLoading.value = false
                    _pokemonList.value = resource.data.orEmpty()
                }
                is Resource.Error -> {
                    _isLoading.value = false
                    _error.value = resource.message
                }
            }
        }.launchIn(viewModelScope)
    }
}
