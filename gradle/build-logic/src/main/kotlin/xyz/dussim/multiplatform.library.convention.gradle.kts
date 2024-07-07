package xyz.dussim

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    kotlin("plugin.parcelize")
    id("xyz.dussim.module.utilities")
}

android {
    compileSdk = 34

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        create("staging") {
            initWith(getByName("debug"))
        }

        release {
            isMinifyEnabled = false
        }
    }
}

kotlin {
    jvmToolchain(21)

    jvm()
    androidTarget()

    sourceSets.commonMain.dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.7.1")
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
