package xyz.dussim

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask
import xyz.dussim.util.libs

class MultiplatformLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit =
        target.run {
            apply<ModuleUtilitiesPlugin>()
            pluginManager.apply("com.android.library")
            pluginManager.apply("org.jetbrains.kotlin.multiplatform")
            pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")
            pluginManager.apply("org.jetbrains.kotlin.plugin.parcelize")

            configure<LibraryExtension> {
                baseConfig()
            }

            configure<KotlinMultiplatformExtension> {
                jvm {
                    compilations.configureEach {
                        compileTaskProvider.configure {
                            compilerOptions.jdk(17)
                        }
                    }
                }
                androidTarget {
                    compilations.configureEach {
                        compileTaskProvider.configure {
                            compilerOptions.jvmTarget(17)
                        }
                    }
                }

                sourceSets.commonMain.dependencies {
                    implementation(libs.kotlinx.coroutines.core)
                    implementation(libs.kotlinx.serialization.core)
                }
            }

            tasks.withType<KotlinCompilationTask<*>> {
                compilerOptions {
                    freeCompilerArgs.addAll(
                        "-opt-in=kotlin.RequiresOptIn",
                        "-Xexpect-actual-classes",
                        "-Xannotation-default-target=param-property",
                    )
                }
            }
        }
}
