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
        kotlinCompilerExtensionVersion = "1.5.5-dev-k2.0.0-Beta1-06b8ae672a4"
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2023.10.01")

    implementation(composeBom)

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.runtime:runtime")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.ui:ui-tooling-preview")

    debugImplementation("androidx.compose.ui:ui-tooling")

    androidTestImplementation(composeBom)
}