plugins {
    alias(libs.plugins.kotlin.android)
    id("com.android.library")
}

android {
    namespace = "com.mariam.android.challenge.core"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    api(project(":core:ui"))
    api(project(":core:navigation"))
    api(project(":core:domain"))
    api(project(":core:data"))
    implementation(libs.androidx.core.ktx)
}