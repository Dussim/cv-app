import buildparameters.BuildParametersExtension
import xyz.dussim.settings.GitRevisionValueSource
import java.nio.file.Files
import java.util.Date

enableFeaturePreview("STABLE_CONFIGURATION_CACHE")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("gradle/build-logic")
    includeBuild("gradle/build-parameters")
    includeBuild("gradle/git-revision-plugin")
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

    versionCatalogs {
        register("conventions") {
            from(files("gradle/conventions.versions.toml"))
        }
        register("ktor") {
            from("io.ktor:ktor-version-catalog:3.3.3")
        }
    }
}

plugins {
    id("com.gradle.develocity").version("4.3")
    id("xyz.dussim.build-parameters")
    id("xyz.dussim.git-revision")
}

develocity {
    val scanLogFile: File =
        layout
            .settingsDirectory
            .file(".reports/scan-journal.log")
            .asFile
    val buildParameters = the<BuildParametersExtension>()
    val gitHash = providers.of(GitRevisionValueSource::class) {}
    buildScan {
        publishing.onlyIf { false }

        termsOfUseUrl = "https://gradle.com/help/legal-terms-of-use"
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
                value("Git Commit ID", gitHash.get())
            }
        }

        buildScanPublished {
            if (!buildParameters.ci) {
                scanLogFile
                    .apply {
                        if (!exists()) {
                            Files.createDirectories(toPath().parent)
                        }
                    }.appendText("${Date()} - $buildScanId - $buildScanUri\n")
            }
        }
    }
}

buildCache {
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
includeBuild("shared")

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
    ":baselineProfile",
)

rootProject.name = "cv-app"
