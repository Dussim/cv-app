package xyz.dussim

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import xyz.dussim.buildlogic.VersioningPlugin

class AndroidAppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = target.run {
        apply<ModuleUtilitiesPlugin>()
        apply<VersioningPlugin>()
        pluginManager.apply("com.android.application")
        pluginManager.apply("org.jetbrains.kotlin.android")
        pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")
        pluginManager.apply("org.jetbrains.kotlin.plugin.parcelize")
        pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

        configure<ApplicationExtension> {
            baseConfig()

            namespace = "xyz.dussim.cv"

            defaultConfig {
                targetSdk = 35
                applicationId = "xyz.dussim.cv"
            }

            buildTypes {
                release {
                    isMinifyEnabled = true
                    isShrinkResources = true
                }
            }
        }

        configure<KotlinAndroidProjectExtension>(AndroidJvmTarget)
    }
}