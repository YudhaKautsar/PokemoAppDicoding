package com.yudha.pokemoapp.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface ISettingRepository {
    fun getThemeSetting(): Flow<Boolean>
    suspend fun saveThemeSetting(isDarkModeActive: Boolean)
    fun getSortSetting(): Flow<String>
    suspend fun saveSortSetting(sortType: String)
}
