# Consumer Proguard Rules for Core Module
-keep class com.yudha.pokemoapp.core.data.remote.response.** { *; }
-keep class com.yudha.pokemoapp.core.data.local.entity.** { *; }
-keep class com.yudha.pokemoapp.core.domain.model.** { *; }

-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

-keep class net.zetetic.database.sqlcipher.** { *; }
-keep class net.zetetic.database.** { *; }
