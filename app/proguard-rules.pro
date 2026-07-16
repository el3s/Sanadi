# Add project specific ProGuard rules here.

-keep class androidx.** { *; }
-keep class com.google.android.** { *; }

# Retrofit
-keepattributes Signature
-keepattributes InnerClasses
-keepattributes EnclosingMethod
-keep class com.sanadi.network.** { *; }
-keep interface com.sanadi.network.** { *; }
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class kotlinx.serialization.**  {
    static <fields> *;
}

# Room
-keep class androidx.room.** { *; }
-keep @androidx.room.Entity class ** { *; }
-dontwarn androidx.room.**

# Kotlin Serialization
-keep,includedescriptorclasses class com.sanadi.**\$\$serializer { *; }
-keepclassmembers class com.sanadi.** {
    *** Companion;
}
-keepclasseswithmembers class com.sanadi.** {
    kotlinx.serialization.KSerializer serializer(...);
}

# Location Services
-keep class com.google.android.gms.location.** { *; }
-keep interface com.google.android.gms.location.** { *; }

# Data Classes
-keepclassmembers class com.sanadi.** {
    *;
}
