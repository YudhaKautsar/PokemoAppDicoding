package com.yudha.pokemoapp.core.domain.usecase

import com.yudha.pokemoapp.core.domain.repository.ISettingRepository

class SaveSortSettingUseCase(private val repository: ISettingRepository) {
    suspend operator fun invoke(sortType: String) {
        repository.saveSortSetting(sortType)
    }
}
