package com.yudha.pokemoapp.core.utils

object Constants {
    // Navigation & Intent Extras
    const val EXTRA_NAME = "extra_name"
    const val FAVORITE_ACTIVITY_CLASS_NAME = "com.yudha.pokemoapp.favorite.FavoriteActivity"

    // API & URL Formats
    const val POKE_API_POKEMON_URL = "https://pokeapi.co/api/v2/pokemon/"
    const val POKEMON_IMAGE_URL_FORMAT = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/%s.png"

    // Sorting Types
    const val SORT_BY_NAME = "name"
    const val SORT_BY_ID = "id"

    // DataStore & Preference Keys
    const val PREFS_SETTINGS_NAME = "settings"
    const val PREF_KEY_THEME = "theme_setting"
    const val PREF_KEY_SORT = "sort_setting"
    const val PREF_KEY_SORT_ORDER = "sort_order_setting"

    // Default Messages & Language
    const val LANGUAGE_EN = "en"
    const val NO_DESCRIPTION_AVAILABLE = "No description available"
    const val ERROR_UNKNOWN = "Unknown Error"
    const val DATABASE_NAME = "pokemon_db"
}
