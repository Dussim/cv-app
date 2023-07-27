package xyz.dussim.buildlogic.internal

import java.io.File

internal class FileVersionSchema(private val file: File) {
    companion object {
        private const val MINOR_KEY = "minor"
        private const val MAJOR_KEY = "major"
        private const val MIN_API_KEY = "minApi"
    }

    private val properties by FilePropertiesDelegate(file)

    val minor: Int get() = properties.getProperty(MINOR_KEY).toInt()
    val major: Int get() = properties.getProperty(MAJOR_KEY).toInt()
    val minApi: Int get() = properties.getProperty(MIN_API_KEY).toInt()

    val versionName: String get() = "${major}.${minor}"
    val versionCode: Int get() = (((minApi * 100 + major) * 100 + minor) * 100)
}