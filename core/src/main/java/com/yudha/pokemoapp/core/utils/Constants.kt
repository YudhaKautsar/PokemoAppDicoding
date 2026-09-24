package com.yudha.pokemoapp.core.utils

object Constants {
    // Navigation & Intent Extras
    const val EXTRA_NAME = "extra_name"
    const val FAVORITE_ACTIVITY_CLASS_NAME = "com.yudha.pokemoapp.favorite.FavoriteActivity"

    // API & URL Formats
    const val POKE_API_POKEMON_URL = "https://pokeapi.co/api/v2/pokemon/"
    const val POKEMON_IMAGE_URL_FORMAT = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/%s.png"

    // Certificate Pinning
    const val API_HOSTNAME = "pokeapi.co"
    const val CERT_PIN_1 = "sha256/vI2c4MzHEbIyjzPN4chWo00EfZeCrlu7OrQuswZxK5Q="
    const val CERT_PIN_2 = "sha256/kIdp6NNEd8wsugYyyIYFsi1ylMCED3hZbSR8ZFsa/A4="
    const val CERT_PIN_3 = "sha256/mEflZT5enoR1FuXLgYYGqnVEoZvmf9c2bVBpiOjYQ0c="

    // Database Encryption & Passphrase
    const val DATABASE_NAME = "pokemon_db"
    const val DB_PASSPHRASE = "pokemoapp_secret_passphrase_key"

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
}
