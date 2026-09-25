# Keep Core Library Public API & Base Classes
-keep class com.yudha.pokemoapp.core.base.** { *; }
-keep class com.yudha.pokemoapp.core.domain.** { *; }
-keep class com.yudha.pokemoapp.core.data.** { *; }
-keep class com.yudha.pokemoapp.core.ui.** { *; }
-keep class com.yudha.pokemoapp.core.di.** { *; }
-keep class com.yudha.pokemoapp.core.utils.** { *; }

# Keep Serialized Model Classes
-keepattributes *Annotation*, Signature
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# SQLCipher Keep Rules
-keep class net.zetetic.database.sqlcipher.** { *; }
-keep class net.zetetic.database.** { *; }
