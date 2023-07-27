package xyz.dussim

plugins {
    id("xyz.dussim.module-utilities")
    id("com.android.library")
    kotlin("android")
    kotlin("plugin.serialization")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 28
        targetSdkVersion(33)
    }

    buildTypes {
        release {
            isMinifyEnabled = true

            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
        explicitApi()
    }
}