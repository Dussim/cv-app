package xyz.dussim

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import xyz.dussim.util.apply
import xyz.dussim.util.ktor
import xyz.dussim.util.libs

class KtorAppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) =
        target.run {
            apply<ModuleUtilitiesPlugin>()
            pluginManager.apply(libs.plugins.application)
            pluginManager.apply(libs.plugins.kotlin.jvm)
            pluginManager.apply(libs.plugins.kotlin.serialization)

            dependencies {
                val implementation = configurations.getByName("implementation")

                implementation(ktor.server.htmlBuilder)
                implementation(ktor.server.core)
                implementation(ktor.server.contentNegotiation)
                implementation(ktor.serialization.kotlinx.json)
                implementation(ktor.server.callLogging)
                implementation(ktor.server.compression)
                implementation(ktor.server.statusPages)
                implementation(ktor.server.resources)
                implementation(ktor.server.netty)
                implementation(libs.logback.classic)
                implementation(libs.bundles.html.css)
            }
        }
}
