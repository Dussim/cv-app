package xyz.dussim

import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.property
import javax.inject.Inject

abstract class VersionSchema @Inject constructor(project: Project) {
    object Defaults {
        const val DEFAULT_MAJOR = 0
        const val DEFAULT_MINOR = 1
        const val DEFAULT_MIN_API = 28
    }

    enum class BuildType(val version: Int) {
        Debug(10), Release(90)
    }

    enum class BuildSubtype(val version: Int) {
        InstantApp(1), NormalApp(3)
    }

    val minApi: Property<Int> = project.objects.property<Int>()
        .apply { finalizeValueOnRead() }
    val major: Property<Int> = project.objects.property<Int>()
        .apply { finalizeValueOnRead() }
    val minor: Property<Int> = project.objects.property<Int>()
        .apply { finalizeValueOnRead() }

    val versionName: Provider<String> = project.provider { "${major.get()}.${minor.get()}" }

    val versionCode: Provider<Int> = project.provider {
        ((minApi.get() * 100 + major.get()) * 100 + minor.get()) * 100
    }
}