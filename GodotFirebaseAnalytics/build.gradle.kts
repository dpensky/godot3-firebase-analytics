plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.dpensky.godotfirebaseanalytics"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // Godot 3.6 library (download from https://godotengine.org/download)
    compileOnly(files("libs/godot-lib.3.6.2.stable.release.aar"))

    // Firebase (use BOM so versions stay compatible)
    implementation(platform("com.google.firebase:firebase-bom:34.11.0"))
    implementation("com.google.firebase:firebase-analytics")
}
