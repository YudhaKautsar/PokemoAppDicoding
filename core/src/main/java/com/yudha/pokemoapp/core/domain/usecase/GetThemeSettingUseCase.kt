package com.yudha.pokemoapp.core.domain.usecase

import com.yudha.pokemoapp.core.domain.repository.ISettingRepository
import kotlinx.coroutines.flow.Flow

class GetThemeSettingUseCase(private val repository: ISettingRepository) {
    operator fun invoke(): Flow<Boolean> = repository.getThemeSetting()
}
