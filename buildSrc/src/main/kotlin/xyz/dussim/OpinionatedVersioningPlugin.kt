package xyz.dussim

import gradle.kotlin.dsl.accessors._b546d458d3a5d3620ec2221a4d3f1868.android
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.register
import xyz.dussim.VersionSchema.Defaults.DEFAULT_MAJOR
import xyz.dussim.VersionSchema.Defaults.DEFAULT_MINOR
import xyz.dussim.VersionSchema.Defaults.DEFAULT_MIN_API

class OpinionatedVersioningPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        android.productFlavors.forEach { flavor ->
            val extension = extensions.create<VersionSchema>("${flavor.name}VersionSchema").apply {
                major.convention(DEFAULT_MAJOR)
                minor.convention(DEFAULT_MINOR)
                minApi.convention(DEFAULT_MIN_API)
            }

            tasks.register<PrintVersionsTask>("${flavor.name}PrintVersions") {
                versionSchema.set(provider { extension })
            }
        }
    }
}