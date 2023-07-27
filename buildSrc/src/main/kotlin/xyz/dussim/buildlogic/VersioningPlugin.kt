package xyz.dussim.buildlogic

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.the

class VersioningPlugin : Plugin<Project> {
    companion object {
        private const val GROUP = "versioning"

        private const val INSTANT_APP_VERSION_OFFSET = 1
        private const val INSTALLED_APP_VERSION_OFFSET = 53
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

        tasks.register<PrintFlavorVersionsTask>("printFlavorVersions") {
            group = GROUP
            description = "Prints the configured version name and code of each flavor"
        }

        with(the<BaseExtension>()) {
            flavorDimensions("installationType")

            productFlavors {
                create("instantApp") {
                    dimension = "installationType"
                    versionCode = extension.versionCode.get() + INSTANT_APP_VERSION_OFFSET
                    versionNameSuffix = "-instant"
                }

                create("installedApp") {
                    dimension = "installationType"
                    versionCode = extension.versionCode.get() + INSTALLED_APP_VERSION_OFFSET
                }

                forEach {
                    it.versionName = extension.versionName.get()
                    it.minSdk = extension.minApi.get()
                }
            }
        }
    }
}