package com.yudha.pokemoapp.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yudha.pokemoapp.core.domain.usecase.GetSortOrderUseCase
import com.yudha.pokemoapp.core.domain.usecase.GetSortSettingUseCase
import com.yudha.pokemoapp.core.domain.usecase.GetThemeSettingUseCase
import com.yudha.pokemoapp.core.domain.usecase.SaveSortOrderUseCase
import com.yudha.pokemoapp.core.domain.usecase.SaveSortSettingUseCase
import com.yudha.pokemoapp.core.domain.usecase.SaveThemeSettingUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    getThemeSettingUseCase: GetThemeSettingUseCase,
    private val saveThemeSettingUseCase: SaveThemeSettingUseCase,
    getSortSettingUseCase: GetSortSettingUseCase,
    private val saveSortSettingUseCase: SaveSortSettingUseCase,
    getSortOrderUseCase: GetSortOrderUseCase,
    private val saveSortOrderUseCase: SaveSortOrderUseCase
) : ViewModel() {

    val themeSetting = getThemeSettingUseCase().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)
    val sortSetting = getSortSettingUseCase().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "name")
    val sortOrder = getSortOrderUseCase().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), true)

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            saveThemeSettingUseCase(isDarkModeActive)
        }
    }

    fun saveSortSetting(sortType: String) {
        viewModelScope.launch {
            saveSortSettingUseCase(sortType)
        }
    }

    fun saveSortOrder(isAscending: Boolean) {
        viewModelScope.launch {
            saveSortOrderUseCase(isAscending)
        }
    }
}
