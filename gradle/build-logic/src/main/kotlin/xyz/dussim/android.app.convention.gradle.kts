package xyz.dussim

plugins {
    id("xyz.dussim.versioning")
    id("xyz.dussim.module.utilities")
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization")
    kotlin("plugin.parcelize")
    kotlin("plugin.compose")
}

android {
    namespace = "xyz.dussim.cv"
    compileSdk = 34

    defaultConfig {
        applicationId = "xyz.dussim.cv"
        targetSdk = 34

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    kotlin {
        jvmToolchain(21)
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    lint {
        abortOnError = false
    }
}