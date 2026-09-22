package com.yudha.pokemoapp.core.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.yudha.pokemoapp.core.domain.repository.ISettingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingPreferences(private val context: Context) : ISettingRepository {

    private val themeKey = booleanPreferencesKey("theme_setting")
    private val sortKey = stringPreferencesKey("sort_setting")
    private val sortOrderKey = booleanPreferencesKey("sort_order_setting")

    override fun getThemeSetting(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[themeKey] ?: false
        }
    }

    override suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[themeKey] = isDarkModeActive
        }
    }

    override fun getSortSetting(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[sortKey] ?: "name"
        }
    }

    override suspend fun saveSortSetting(sortType: String) {
        context.dataStore.edit { preferences ->
            preferences[sortKey] = sortType
        }
    }

    override fun getSortOrder(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[sortOrderKey] ?: true
        }
    }

    override suspend fun saveSortOrder(isAscending: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[sortOrderKey] = isAscending
        }
    }
}
