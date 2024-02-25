package xyz.dussim.buildlogic

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.the

abstract class PrintVersionsTask : DefaultTask() {

    @TaskAction
    fun printVersions() {
        val extension = project.the<FileVersioningExtension>()

        println("Version name base: ${extension.versionName.get()}")
        println("Version code base: ${extension.versionCode.get().toString().dropLast(2) + "XX"}")
    }
}