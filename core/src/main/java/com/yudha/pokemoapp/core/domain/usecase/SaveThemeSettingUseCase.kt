package com.yudha.pokemoapp.core.domain.usecase

import com.yudha.pokemoapp.core.domain.repository.ISettingRepository

class SaveThemeSettingUseCase(private val repository: ISettingRepository) {
    suspend operator fun invoke(isDarkModeActive: Boolean) {
        repository.saveThemeSetting(isDarkModeActive)
    }
}
