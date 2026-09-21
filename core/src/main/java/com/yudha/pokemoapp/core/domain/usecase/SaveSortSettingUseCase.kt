package com.yudha.pokemoapp.core.domain.usecase

import com.yudha.pokemoapp.core.data.preferences.SettingPreferences

class SaveSortSettingUseCase(private val preferences: SettingPreferences) {
    suspend operator fun invoke(sortType: String) {
        preferences.saveSortSetting(sortType)
    }
}
