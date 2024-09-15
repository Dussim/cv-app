package xyz.dussim

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

class MultiplatformLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = target.run {
        apply<ModuleUtilitiesPlugin>()
        pluginManager.apply("com.android.library")
        pluginManager.apply("org.jetbrains.kotlin.multiplatform")
        pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")
        pluginManager.apply("org.jetbrains.kotlin.plugin.parcelize")

        configure<LibraryExtension> {
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

            testOptions {
                targetSdk = 34
            }

            buildFeatures {

            }
        }

        configure<KotlinMultiplatformExtension> {
            jvmToolchain(21)

            jvm()
            androidTarget()

            sourceSets.commonMain.dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.7.1")
            }
        }

        tasks.withType<KotlinCompilationTask<*>> {
            compilerOptions {
                freeCompilerArgs.addAll(
                    "-opt-in=kotlin.RequiresOptIn",
                    "-Xexpect-actual-classes"
                )
            }
        }
    }
}