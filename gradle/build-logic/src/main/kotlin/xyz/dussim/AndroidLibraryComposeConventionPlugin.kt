package xyz.dussim

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = target.run {
        apply<AndroidLibraryConventionPlugin>()
        pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

        dependencies {
            val composeBom = platform("androidx.compose:compose-bom:2024.06.00")

            "implementation"(composeBom)

            "implementation"("androidx.compose.ui:ui")
            "implementation"("androidx.compose.ui:ui-graphics")
            "implementation"("androidx.compose.runtime:runtime")
            "implementation"("androidx.compose.foundation:foundation")
            "implementation"("androidx.compose.ui:ui-tooling-preview")

            "debugImplementation"("androidx.compose.ui:ui-tooling")

            "androidTestImplementation"(composeBom)
        }
    }
}