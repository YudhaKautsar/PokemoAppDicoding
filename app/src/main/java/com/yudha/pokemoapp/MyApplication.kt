package com.yudha.pokemoapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.yudha.pokemoapp.core.di.databaseModule
import com.yudha.pokemoapp.core.di.networkModule
import com.yudha.pokemoapp.core.di.repositoryModule
import com.yudha.pokemoapp.core.di.useCaseModule
import com.yudha.pokemoapp.core.domain.usecase.GetThemeSettingUseCase
import com.yudha.pokemoapp.di.viewModelModule
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(networkModule, databaseModule, repositoryModule, useCaseModule, viewModelModule))
        }

        val getThemeSettingUseCase: GetThemeSettingUseCase by inject()
        MainScope().launch {
            val isDarkModeActive = getThemeSettingUseCase().first()
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}
