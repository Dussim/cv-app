import buildparameters.BuildParametersExtension
import org.gradle.kotlin.dsl.support.serviceOf
import java.io.ByteArrayOutputStream
import java.nio.file.Files
import java.util.Date

enableFeaturePreview("GROOVY_COMPILATION_AVOIDANCE")

pluginManagement {
    includeBuild("gradle/build-logic")
    includeBuild("gradle/build-parameters")
    repositories {
        google {
            content {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google.testing")
                includeGroupAndSubgroups("com.google.android")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google {
            content {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google.testing")
                includeGroupAndSubgroups("com.google.android")
            }
        }
        mavenCentral()
    }
}

plugins {
    id("com.gradle.develocity").version("3.17.4")
    id("xyz.dussim.build-parameters")
}

develocity {
    val execOps = serviceOf<ExecOperations>()
    val buildParameters = the<BuildParametersExtension>()

    buildScan {
        publishing.onlyIf { false }

        termsOfUseUrl = "https://gradle.com/terms-of-service"
        termsOfUseAgree = "yes"

        capture {
            buildLogging = true
            testLogging = true
        }

        tag(
            when (buildParameters.ci) {
                true -> "CI"
                false -> "LOCAL"
            },
        )

        background {
            if (!buildParameters.ci) {
                val output = ByteArrayOutputStream()
                execOps.exec {
                    commandLine("git", "rev-parse", "--short", "HEAD")
                    standardOutput = output
                }
                value("Git Commit ID", output.toString())
            }
        }

        buildScanPublished {
            if (!buildParameters.ci) {
                file(".reports/scan-journal.log")
                    .apply {
                        if (!exists()) {
                            Files.createDirectories(toPath().parent)
                        }
                    }
                    .appendText("${Date()} - $buildScanId - $buildScanUri\n")
            }
        }
    }
}

buildCache {
    val buildParameters = the<BuildParametersExtension>()
    local {
        isEnabled = !buildParameters.ci
        isPush = true
    }

    remote<HttpBuildCache> {
        isPush = buildParameters.ci

        url = uri(buildParameters.cache.url)

        credentials {
            username = buildParameters.cache.username
            password = buildParameters.cache.password
        }
    }
}

includeBuild("backend")
includeBuild("deployment")
includeBuild("shared") {
    dependencySubstitution {
        substitute(module("xyz.dussim:data-model")).using(project(":data-model"))
    }
}

include(
    // Layer 1 - Modules that are not dependent on any other project module
    ":core:data",
    ":core:design-system",
    // Layer 2 - Modules that are dependent on Layer 1 modules
    ":core:ui",
    ":core:api",
    ":core:navigation",
    // Layer 3 - Modules that are dependent on previous layers
    ":core:api-compose",
    ":core:network",
    ":core:local",
    ":core:model",
    // Features Layer - Modules that are dependent on previous layers and provide chunks of app functionality
    ":feature:splash-screen",
    ":feature:cv-content",
    ":feature:easter-eggs:gym",
    // Top layer - Top level modules that are dependent on previous layers
    ":app",
)
