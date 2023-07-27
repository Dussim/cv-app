package xyz.dussim

plugins {
    id("xyz.dussim.module-utilities")
    id("xyz.dussim.android-library-convention")
}

android {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2023.06.01")

    implementation(composeBom)
    androidTestImplementation(composeBom)
}