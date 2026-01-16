package xyz.dussim

import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions

internal fun KotlinJvmCompilerOptions.jdkRelease(version: Int) {
    freeCompilerArgs.addAll(
        "-Xjdk-release=$version",
        "-Xannotation-default-target=param-property",
    )
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
