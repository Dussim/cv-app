package xyz.dussim

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import xyz.dussim.util.libs

class MultiplatformLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit =
        target.run {
            apply<ModuleUtilitiesPlugin>()
            pluginManager.apply("org.jetbrains.kotlin.multiplatform")
            pluginManager.apply("com.android.kotlin.multiplatform.library")
            pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")
            pluginManager.apply("org.jetbrains.kotlin.plugin.parcelize")

            configure<KotlinMultiplatformExtension> {
                compilerOptions.freeCompilerArgs.addAll(
                    "-opt-in=kotlin.RequiresOptIn",
                    "-Xexpect-actual-classes",
                    "-Xannotation-default-target=param-property",
                )

                jvm {
                    compilerOptions.jdk(17)
                }

                configure<KotlinMultiplatformAndroidLibraryTarget> {
                    compileSdk = 36
                    minSdk = 28

                    androidResources.enable = true

                    withDeviceTest {
                        instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                        execution = "HOST"
                    }

                    compilerOptions.jvmTarget(17)
                }

                sourceSets.commonMain.dependencies {
                    implementation(libs.kotlinx.coroutines.core)
                    implementation(libs.kotlinx.serialization.core)
                }
            }
        }
}
