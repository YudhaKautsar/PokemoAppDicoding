package com.yudha.pokemoapp.core.domain.usecase

import com.yudha.pokemoapp.core.data.preferences.SettingPreferences
import kotlinx.coroutines.flow.Flow

class GetSortSettingUseCase(private val preferences: SettingPreferences) {
    operator fun invoke(): Flow<String> = preferences.getSortSetting()
}
