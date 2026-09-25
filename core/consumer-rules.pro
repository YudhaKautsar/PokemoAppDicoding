# Consumer Proguard Rules for Core Module
-keep class com.yudha.pokemoapp.core.base.** { *; }
-keep class com.yudha.pokemoapp.core.domain.** { *; }
-keep class com.yudha.pokemoapp.core.data.** { *; }
-keep class com.yudha.pokemoapp.core.ui.** { *; }
-keep class com.yudha.pokemoapp.core.di.** { *; }
-keep class com.yudha.pokemoapp.core.utils.** { *; }

-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

-keep class net.zetetic.database.sqlcipher.** { *; }
-keep class net.zetetic.database.** { *; }
-keepclasseswithmembernames class * {
    native <methods>;
}
