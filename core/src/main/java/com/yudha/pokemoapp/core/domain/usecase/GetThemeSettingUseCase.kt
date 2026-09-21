package com.yudha.pokemoapp.core.domain.usecase

import com.yudha.pokemoapp.core.data.preferences.SettingPreferences
import kotlinx.coroutines.flow.Flow

class GetThemeSettingUseCase(private val preferences: SettingPreferences) {
    operator fun invoke(): Flow<Boolean> = preferences.getThemeSetting()
}
