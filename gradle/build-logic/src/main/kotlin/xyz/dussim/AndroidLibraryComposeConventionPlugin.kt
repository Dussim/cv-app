package xyz.dussim

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import xyz.dussim.util.libs

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = target.run {
        apply<AndroidLibraryConventionPlugin>()
        pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

        dependencies {
            val implementation = configurations.getByName("implementation")
            val debugImplementation = configurations.getByName("debugImplementation")

            implementation(platform(libs.androidx.compose.bom))

            implementation(libs.androidx.compose.ui)
            implementation(libs.androidx.compose.ui.graphics)
            implementation(libs.androidx.compose.runtime)
            implementation(libs.androidx.compose.foundation)
            implementation(libs.androidx.compose.ui.tooling.preview)

            debugImplementation(libs.androidx.compose.ui.tooling)
        }
    }
}