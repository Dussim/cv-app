package xyz.dussim

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class PrintVersionsTask : DefaultTask() {

    @get:Input
    abstract val versionSchema: Property<VersionSchema>

    @TaskAction
    fun printVersions() = with(versionSchema.get()) {
        println("Lo and behold I'm printing something important")
        println(minApi.get())
        println(major.get())
        println(minor.get())
        println()
        println(versionName.get())
        println(versionCode.get())
    }
}