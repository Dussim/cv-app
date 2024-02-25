package xyz.dussim.buildlogic

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.TaskAction
import java.util.*

abstract class IncrementMinorTask : DefaultTask() {
    @get:InputFile
    val propertiesFile: RegularFileProperty = project.objects.fileProperty()

    @TaskAction
    fun incrementMinor() {
        val file = propertiesFile.asFile.get()
        Properties().apply {
            load(file.inputStream())
            val minor = getProperty("minor").toInt()
            setProperty("minor", (minor + 1).toString())
            store(file.outputStream(), null)
        }
    }
}