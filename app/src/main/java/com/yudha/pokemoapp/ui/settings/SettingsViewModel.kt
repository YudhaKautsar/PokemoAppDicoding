package com.yudha.pokemoapp.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yudha.pokemoapp.core.domain.usecase.GetSortSettingUseCase
import com.yudha.pokemoapp.core.domain.usecase.GetThemeSettingUseCase
import com.yudha.pokemoapp.core.domain.usecase.SaveSortSettingUseCase
import com.yudha.pokemoapp.core.domain.usecase.SaveThemeSettingUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val getThemeSettingUseCase: GetThemeSettingUseCase,
    private val saveThemeSettingUseCase: SaveThemeSettingUseCase,
    private val getSortSettingUseCase: GetSortSettingUseCase,
    private val saveSortSettingUseCase: SaveSortSettingUseCase
) : ViewModel() {

    val themeSetting = getThemeSettingUseCase().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)
    val sortSetting = getSortSettingUseCase().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), "name")

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
}
