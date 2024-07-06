package xyz.dussim

plugins {
    id("xyz.dussim.module.utilities")
    id("xyz.dussim.android.library.convention")
    id("org.jetbrains.kotlin.plugin.compose")
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2024.06.00")

    implementation(composeBom)

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.runtime:runtime")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.ui:ui-tooling-preview")

    debugImplementation("androidx.compose.ui:ui-tooling")

    androidTestImplementation(composeBom)
}