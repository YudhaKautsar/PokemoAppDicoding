# Keep Data Models & Serialized Classes (Gson / Room)
-keep class com.yudha.pokemoapp.core.data.remote.response.** { *; }
-keep class com.yudha.pokemoapp.core.data.local.entity.** { *; }
-keep class com.yudha.pokemoapp.core.domain.model.** { *; }

# Gson Keep Rules
-keepattributes *Annotation*, Signature
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Retrofit Keep Rules
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
-keepclassmembers enum * { *; }

# Room Keep Rules
-keep class * extends androidx.room.RoomDatabase

# SQLCipher Keep Rules
-keep class net.zetetic.database.sqlcipher.** { *; }
-keep class net.zetetic.database.** { *; }

# Glide Keep Rules
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
