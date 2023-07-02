package xyz.dussim

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.register
import xyz.dussim.VersionSchema.Defaults.DEFAULT_MAJOR
import xyz.dussim.VersionSchema.Defaults.DEFAULT_MINOR
import xyz.dussim.VersionSchema.Defaults.DEFAULT_MIN_API

class OpinionatedVersioningPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        val extension = extensions.create<VersionSchema>("versionSchema")

        extension.major.convention(DEFAULT_MAJOR)
        extension.minor.convention(DEFAULT_MINOR)
        extension.minApi.convention(DEFAULT_MIN_API)

        tasks.register<PrintVersionsTask>("printVersions") {
            versionSchema.set(provider { extension })
        }
    }
}