package xyz.dussim

plugins {
    id("xyz.dussim.module.utilities")
    id("com.android.library")
    kotlin("android")
    kotlin("plugin.serialization")
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
        release {
            isMinifyEnabled = true

            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    kotlin {
        jvmToolchain(21)
    }
}

project.project