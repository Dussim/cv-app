package xyz.dussim

import gradle.kotlin.dsl.accessors._b546d458d3a5d3620ec2221a4d3f1868.android
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.create
import xyz.dussim.VersionSchema.BuildSubtype

interface BuildSubtypeHolder {
    val subType: Property<BuildSubtype>
}

class OpinionatedFlavorsPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target.android) {
        flavorDimensions += "installationType"

        productFlavors {
            create("instantApp") {
                dimension = "installationType"

                val extension = target.createExtension(name)

                extension.subType.set(BuildSubtype.InstantApp)
            }
            create("installedApp") {
                dimension = "installationType"

                val extension = target.createExtension(name)

                extension.subType.set(BuildSubtype.InstalledApp)
            }
        }
    }

    private fun Project.createExtension(flavorName: String): BuildSubtypeHolder {
        return extensions.create<BuildSubtypeHolder>("${flavorName}BuildSubTypeHolder")
    }
}
