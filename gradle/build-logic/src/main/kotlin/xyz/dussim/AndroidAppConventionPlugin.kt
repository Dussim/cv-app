package xyz.dussim

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import xyz.dussim.buildlogic.VersioningPlugin
import xyz.dussim.util.apply
import xyz.dussim.util.libs

class AndroidAppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = target.run {
        apply<ModuleUtilitiesPlugin>()
        apply<VersioningPlugin>()
        pluginManager.apply(libs.plugins.android.application)
        pluginManager.apply(libs.plugins.kotlin.android)
        pluginManager.apply(libs.plugins.kotlin.serialization)
        pluginManager.apply(libs.plugins.kotlin.parcelize)
        pluginManager.apply(libs.plugins.kotlin.compose)

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