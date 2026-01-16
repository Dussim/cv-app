package xyz.dussim

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
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
            pluginManager.apply(libs.plugins.kotlin.serialization)
            pluginManager.apply(libs.plugins.kotlin.parcelize)

            configure<LibraryExtension> {
                compileSdk = 36

                defaultConfig {
                    minSdk = 28
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    vectorDrawables.useSupportLibrary = true
                }

                buildTypes.register("staging") {
                    initWith(buildTypes.getByName("debug"))
                }

                buildTypes.named("release") {
                    proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                }

                packaging.resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"

                lint.abortOnError = false

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }

                testOptions.targetSdk = 36

                testFixtures {
                    enable = true
                    androidResources = true
                }
            }

            configure<KotlinAndroidProjectExtension>(AndroidJvmTarget)
        }
}
