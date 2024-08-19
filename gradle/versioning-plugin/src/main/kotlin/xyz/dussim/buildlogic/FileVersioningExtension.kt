package xyz.dussim.buildlogic

import org.gradle.api.file.RegularFileProperty
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.property
import xyz.dussim.buildlogic.internal.FileVersionSchema
import javax.inject.Inject

abstract class FileVersioningExtension @Inject constructor(
    factory:ObjectFactory
) {
    val propertiesFile: RegularFileProperty = factory.fileProperty()
        .apply { finalizeValueOnRead() }

    private val fileVersionSchema: Property<FileVersionSchema> = factory.property<FileVersionSchema>()
        .convention(propertiesFile.map {  FileVersionSchema(it.asFile) })

    private val versionProperties: Provider<FileVersionSchema> = factory.property<FileVersionSchema>()
        .convention(fileVersionSchema)

    val minor: Provider<Int> get() = versionProperties.map { it.minor }
    val major: Provider<Int> get() = versionProperties.map { it.major }
    val minApi: Provider<Int> get() = versionProperties.map { it.minApi }

    val versionName: Provider<String> get() = versionProperties.map { it.versionName }
    val versionCode: Provider<Int> get() = versionProperties.map { it.versionCode }
}