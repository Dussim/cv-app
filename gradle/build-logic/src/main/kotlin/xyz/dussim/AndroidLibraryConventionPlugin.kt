package xyz.dussim

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import xyz.dussim.util.apply
import xyz.dussim.util.libs

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) =
        target.run {
            apply<ModuleUtilitiesPlugin>()
            pluginManager.apply(libs.plugins.android.library)
            pluginManager.apply(libs.plugins.kotlin.android)
            pluginManager.apply(libs.plugins.kotlin.serialization)
            pluginManager.apply(libs.plugins.kotlin.parcelize)

            configure<LibraryExtension> {
                baseConfig()

                testFixtures {
                    enable = true
                    androidResources = true
                }
            }

            configure<KotlinAndroidProjectExtension>(AndroidJvmTarget)

            dependencies {
                "testFixturesCompileOnly"(libs.kotlin.stdlib)
            }
        }
}
