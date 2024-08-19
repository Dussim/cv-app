package xyz.dussim

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = target.run {
        apply<AndroidLibraryConventionPlugin>()
        dependencies {
            "implementation"(project(":core:api"))
        }
    }
}