package xyz.dussim

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    kotlin("plugin.parcelize")
}

android {
    compileSdk = 34

    defaultConfig {
        minSdk = 28
        targetSdk = 34

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        create("staging") {
            initWith(getByName("debug"))
        }
    }
}

kotlin {
    jvmToolchain(21)

    jvm()
    androidTarget()

    sourceSets.commonMain.dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.6.3")
    }

    targets.all {
        compilations.all {
            kotlinOptions {
                freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
                freeCompilerArgs += "-Xexpect-actual-classes"
            }
        }
    }
}
