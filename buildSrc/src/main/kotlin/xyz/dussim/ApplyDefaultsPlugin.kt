package xyz.dussim

import gradle.kotlin.dsl.accessors._b546d458d3a5d3620ec2221a4d3f1868.android
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByName

class ApplyDefaultsPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        android.productFlavors {
            forEach { flavor ->
                val versionSchema = target.extensions.getByName<VersionSchema>("${flavor.name}VersionSchema")
                val buildSubtype = target.extensions.getByName<BuildSubtypeHolder>("${flavor.name}BuildSubTypeHolder")

                versionSchema.buildSubType.set(buildSubtype.subType)

                flavor.versionCode = versionSchema.versionCode.get()
                flavor.versionName = versionSchema.versionName.get()
            }
        }
    }
}