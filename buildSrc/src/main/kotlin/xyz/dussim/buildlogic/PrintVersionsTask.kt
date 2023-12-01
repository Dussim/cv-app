package xyz.dussim.buildlogic

import com.android.build.gradle.BaseExtension
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

abstract class DescribeVersionCodeTask : DefaultTask() {

    @TaskAction
    fun describeVersionCode() {
        val extension = project.the<FileVersioningExtension>()

        println("Version code base: ${extension.versionCode.get().toString().dropLast(2) + "XX"}")
        println("Where:")
        println("${extension.minApi.get()}______ configured min sdk")
        println("__${extension.major.get().toString().padStart(2, '0')}____ configured major version")
        println("____${extension.minor.get().toString().padStart(2, '0')}__ configured minor version")
        println("______XX flavor dependant offset")
    }
}

abstract class PrintFlavorVersionsTask : DefaultTask() {
    @TaskAction
    fun printFlavorVersions() {
        with(the<BaseExtension>()) {
            productFlavors.forEach { flavor ->
                println("Flavor: ${flavor.name}")
                println("Version name: ${flavor.versionName + flavor.versionNameSuffix.orEmpty()}")
                println("Version code: ${flavor.versionCode}")
                println()
            }
        }
    }
}