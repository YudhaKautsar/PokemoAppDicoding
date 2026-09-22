package com.yudha.pokemoapp.core.di

import androidx.room.Room
import com.yudha.pokemoapp.core.BuildConfig
import com.yudha.pokemoapp.core.data.local.database.AppDatabase
import com.yudha.pokemoapp.core.data.preferences.SettingPreferences
import com.yudha.pokemoapp.core.data.remote.ApiService
import com.yudha.pokemoapp.core.data.repository.PokemonRepositoryImpl
import com.yudha.pokemoapp.core.domain.repository.ISettingRepository
import com.yudha.pokemoapp.core.domain.repository.PokemonRepository
import com.yudha.pokemoapp.core.domain.usecase.GetFavoritePokemonUseCase
import com.yudha.pokemoapp.core.domain.usecase.GetFavoriteStatusUseCase
import com.yudha.pokemoapp.core.domain.usecase.GetSortSettingUseCase
import com.yudha.pokemoapp.core.domain.usecase.GetThemeSettingUseCase
import com.yudha.pokemoapp.core.domain.usecase.GetPokemonDetailUseCase
import com.yudha.pokemoapp.core.domain.usecase.GetPokemonListUseCase
import com.yudha.pokemoapp.core.domain.usecase.SaveSortSettingUseCase
import com.yudha.pokemoapp.core.domain.usecase.SaveThemeSettingUseCase
import com.yudha.pokemoapp.core.domain.usecase.ToggleFavoriteUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(ApiService::class.java) }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "pokemon_db"
        ).build()
    }
    single { get<AppDatabase>().pokemonDao() }
    single<ISettingRepository> { SettingPreferences(androidApplication()) }
}

val repositoryModule = module {
    single<PokemonRepository> { PokemonRepositoryImpl(get(), get()) }
}

val useCaseModule = module {
    single { GetPokemonListUseCase(get()) }
    single { GetPokemonDetailUseCase(get()) }
    single { GetFavoritePokemonUseCase(get()) }
    single { GetFavoriteStatusUseCase(get()) }
    single { ToggleFavoriteUseCase(get()) }
    single { GetThemeSettingUseCase(get()) }
    single { SaveThemeSettingUseCase(get()) }
    single { GetSortSettingUseCase(get()) }
    single { SaveSortSettingUseCase(get()) }
}
