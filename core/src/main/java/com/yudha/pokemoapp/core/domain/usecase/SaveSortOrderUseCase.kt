package com.yudha.pokemoapp.core.domain.usecase

import com.yudha.pokemoapp.core.domain.repository.ISettingRepository

class SaveSortOrderUseCase(private val repository: ISettingRepository) {
    suspend operator fun invoke(isAscending: Boolean) {
        repository.saveSortOrder(isAscending)
    }
}
