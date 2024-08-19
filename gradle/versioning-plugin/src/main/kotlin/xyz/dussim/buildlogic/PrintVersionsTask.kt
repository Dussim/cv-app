package xyz.dussim.buildlogic

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class PrintVersionsTask : DefaultTask() {

    @get:Input
    abstract val versionName: Property<String>

    @get:Input
    abstract val versionCode: Property<Int>

    @TaskAction
    fun printVersions() {
        println("Version name base: ${versionName.get()}")
        println("Version code base: ${versionCode.get().toString().dropLast(2) + "XX"}")
    }
}