package xyz.dussim

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import xyz.dussim.util.libs

class AndroidFeatureComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = target.run {
        apply<AndroidLibraryComposeConventionPlugin>()
        dependencies {
            "api"(project(":core:navigation"))

            "implementation"(libs.bundles.voyager)
        }
    }
}