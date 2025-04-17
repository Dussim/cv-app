package xyz.dussim

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import xyz.dussim.util.apply
import xyz.dussim.util.libs

class KtorAppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = target.run {
        apply<ModuleUtilitiesPlugin>()
        pluginManager.apply(libs.plugins.application)
        pluginManager.apply(libs.plugins.kotlin.jvm)
        pluginManager.apply(libs.plugins.kotlin.serialization)

        dependencies {
            "implementation"(libs.bundles.ktor.server)
            "implementation"(libs.bundles.html.css)
        }
    }
}
