package xyz.dussim

plugins {
    id("xyz.dussim.module.utilities")
    id("xyz.dussim.android.library.convention")
}

android {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11-dev-k2.0.0-Beta4-21f5e479a96"
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2024.02.01")

    implementation(composeBom)

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.runtime:runtime")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.ui:ui-tooling-preview")

    debugImplementation("androidx.compose.ui:ui-tooling")

    androidTestImplementation(composeBom)
}