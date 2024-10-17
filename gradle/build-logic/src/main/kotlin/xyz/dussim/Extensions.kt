package xyz.dussim

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions

internal fun CommonExtension<*, *, *, *, *, *>.baseConfig() {
    compileSdk = 35

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        register("staging") {
            initWith(getByName("debug"))
        }

        getByName("release") {
            isMinifyEnabled = false
            isShrinkResources = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    lint {
        abortOnError = false
    }

    compileOptions {
//        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    (this as? LibraryExtension)?.testOptions {
        targetSdk = 35
    }
}

internal fun KotlinJvmCompilerOptions.jdkRelease(version: Int) {
    freeCompilerArgs.add("-Xjdk-release=${version}")
}

internal fun KotlinJvmCompilerOptions.jvmTarget(version: Int) {
    jvmTarget.set(JvmTarget.fromTarget(version.toString()))
}

internal fun KotlinJvmCompilerOptions.jdk(version: Int) {
    jvmTarget(version)
    jdkRelease(version)
}

internal val AndroidJvmTarget: KotlinAndroidProjectExtension.() -> Unit = {
    this.target.compilations.configureEach {
        compileTaskProvider.configure {
            compilerOptions.jvmTarget(17)
        }
    }
}