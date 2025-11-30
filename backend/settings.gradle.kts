enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

pluginManagement {
    includeBuild("../gradle/build-logic")
    repositories {
        google {
            content {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google.testing")
                includeGroupAndSubgroups("com.google.android")
            }
        }
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories.mavenCentral()

    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
        create("conventions") {
            from(files("../gradle/conventions.versions.toml"))
        }
        create("ktor") {
            from("io.ktor:ktor-version-catalog:3.3.2")
        }
    }
}

includeBuild("../shared")

rootProject.name = "backend"
