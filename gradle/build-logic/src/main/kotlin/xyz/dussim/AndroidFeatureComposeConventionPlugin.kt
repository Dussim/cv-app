package xyz.dussim

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = target.run {
        apply<AndroidLibraryComposeConventionPlugin>()
        dependencies {
            val voyagerVersion = "1.0.0"
            "api"(project(":core:navigation"))

            "implementation"("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")
            "implementation"("cafe.adriel.voyager:voyager-screenmodel:$voyagerVersion")
        }
    }
}