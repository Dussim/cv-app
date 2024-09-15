package xyz.dussim

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = target.run {
        apply<ModuleUtilitiesPlugin>()
        pluginManager.apply("com.android.library")
        pluginManager.apply("org.jetbrains.kotlin.android")
        pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")
        pluginManager.apply("org.jetbrains.kotlin.plugin.parcelize")

        configure<LibraryExtension> {
            baseConfig()

            testFixtures {
                enable = true
                androidResources = true
            }
        }

        configure<KotlinAndroidProjectExtension>(AndroidJvmTarget)

        dependencies {
            "testFixturesCompileOnly"("org.jetbrains.kotlin:kotlin-stdlib:2.1.0-Beta2")
        }
    }
}
