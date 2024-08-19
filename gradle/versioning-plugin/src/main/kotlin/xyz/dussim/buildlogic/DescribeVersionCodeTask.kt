package xyz.dussim.buildlogic

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class DescribeVersionCodeTask : DefaultTask() {

    @get:Input
    abstract val versionCode: Property<Int>

    @get:Input
    abstract val minApi: Property<Int>

    @get:Input
    abstract val major: Property<Int>

    @get:Input
    abstract val minor: Property<Int>

    @TaskAction
    fun describeVersionCode() {
        println("Version code base: ${versionCode.get().toString().dropLast(2) + "XX"}")
        println("Where:")
        println("${minApi.get()}______ configured min sdk")
        println("__${major.get().toString().padStart(2, '0')}____ configured major version")
        println("____${minor.get().toString().padStart(2, '0')}__ configured minor version")
        println("______XX flavor dependant offset")
    }
}