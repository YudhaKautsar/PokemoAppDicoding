package com.yudha.pokemoapp.di

import com.yudha.pokemoapp.ui.list.PokemonListViewModel
import com.yudha.pokemoapp.ui.detail.PokemonDetailViewModel
import com.yudha.pokemoapp.ui.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PokemonListViewModel(get(), get(), get()) }
    viewModel { PokemonDetailViewModel(get(), get(), get()) }
    viewModel { SettingsViewModel(get(), get(), get(), get(), get(), get()) }
}
