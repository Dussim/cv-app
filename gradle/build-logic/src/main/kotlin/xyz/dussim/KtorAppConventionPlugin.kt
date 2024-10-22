package xyz.dussim

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class KtorAppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = target.run {
        apply<ModuleUtilitiesPlugin>()
        pluginManager.apply("org.gradle.application")
        pluginManager.apply("org.jetbrains.kotlin.jvm")
        pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")

        dependencies {
            val ktorVersion = "3.0.0"
            "implementation"("io.ktor:ktor-server-html-builder-jvm:$ktorVersion")
            "implementation"("io.ktor:ktor-server-core-jvm:$ktorVersion")
            "implementation"("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
            "implementation"("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktorVersion")
            "implementation"("io.ktor:ktor-server-call-logging-jvm:$ktorVersion")
            "implementation"("io.ktor:ktor-server-compression-jvm:$ktorVersion")
            "implementation"("io.ktor:ktor-server-host-common-jvm:$ktorVersion")
            "implementation"("io.ktor:ktor-server-status-pages-jvm:$ktorVersion")
            "implementation"("io.ktor:ktor-server-resources:$ktorVersion")
            "implementation"("io.ktor:ktor-server-netty-jvm:$ktorVersion")
            "implementation"("ch.qos.logback:logback-classic:1.5.6")

            "implementation"("org.jetbrains.kotlinx:kotlinx-html-jvm:0.11.0")
            "implementation"("org.jetbrains.kotlin-wrappers:kotlin-css:1.0.0-pre.625")
        }
    }
}