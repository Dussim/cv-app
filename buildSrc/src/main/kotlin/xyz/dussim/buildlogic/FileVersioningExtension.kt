package xyz.dussim.buildlogic

import xyz.dussim.buildlogic.internal.FileVersionSchema
import org.gradle.api.Project
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Provider
import javax.inject.Inject

abstract class FileVersioningExtension @Inject constructor(
    private val project: Project
) {
    private val fileVersionSchema: Lazy<FileVersionSchema> = lazy {
        FileVersionSchema(propertiesFile.get().asFile)
    }

    private val versionProperties: Provider<FileVersionSchema> = project.provider(fileVersionSchema::value)

    val propertiesFile: RegularFileProperty = project.objects.fileProperty()
        .apply { finalizeValueOnRead() }
        .convention(project.rootProject.layout.projectDirectory.file("version.properties"))


    val minor: Provider<Int> get() = versionProperties.map { it.minor }
    val major: Provider<Int> get() = versionProperties.map { it.major }
    val minApi: Provider<Int> get() = versionProperties.map { it.minApi }

    val versionName: Provider<String> get() = versionProperties.map { it.versionName }
    val versionCode: Provider<Int> get() = versionProperties.map { it.versionCode }
}