package xyz.dussim

plugins {
    id("xyz.dussim.android.library.compose.convention")
    kotlin("plugin.parcelize")
}

dependencies {
    val voyagerVersion = "1.0.0"
    api(project(":core:navigation"))

    implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")
    implementation("cafe.adriel.voyager:voyager-screenmodel:$voyagerVersion")
}
