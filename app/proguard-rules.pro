# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/harshyadav/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.kts.

# Keep Compose view models & state
-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}
