package xyz.dussim.buildlogic

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.TaskAction
import java.util.Properties

abstract class IncrementMajorTask : DefaultTask() {
    @get:InputFile
    abstract val propertiesFile: RegularFileProperty

    @TaskAction
    fun incrementMinor() {
        val file = propertiesFile.asFile.get()
        Properties().apply {
            load(file.inputStream())
            val major = getProperty("major").toInt()
            setProperty("major", (major + 1).toString())
            setProperty("minor", "0")
            store(file.outputStream(), null)
        }
    }
}