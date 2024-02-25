package xyz.dussim.buildlogic

import java.util.Properties
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.register

class VersioningPlugin : Plugin<Project> {

    companion object {
        private const val GROUP = "versioning"
    }

    override fun apply(target: Project): Unit = with(target) {
        val extension = extensions.create<FileVersioningExtension>("versioning")

        tasks.register<PrintVersionsTask>("printVersions") {
            group = GROUP
            description = "Prints the configured base version name and code"
        }

        tasks.register<DescribeVersionCodeTask>("describeVersionCode") {
            group = GROUP
            description = "Prints description of the configured version code"
        }

        tasks.register<IncrementMajorTask>("incrementMajor") {
            group = GROUP
            propertiesFile.set(extension.propertiesFile)
        }

        tasks.register<IncrementMinorTask>("incrementMinor") {
            group = GROUP
            propertiesFile.set(extension.propertiesFile)
        }
    }
}