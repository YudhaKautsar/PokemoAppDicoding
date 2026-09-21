package com.yudha.pokemoapp.core.domain.usecase

import com.yudha.pokemoapp.core.data.preferences.SettingPreferences

class SaveThemeSettingUseCase(private val preferences: SettingPreferences) {
    suspend operator fun invoke(isDarkModeActive: Boolean) {
        preferences.saveThemeSetting(isDarkModeActive)
    }
}
