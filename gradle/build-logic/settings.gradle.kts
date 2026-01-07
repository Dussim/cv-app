enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

pluginManagement {
    includeBuild("../versioning-plugin")
    repositories.gradlePluginPortal()
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
        gradlePluginPortal()
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            from(files("../libs.versions.toml"))
        }
        create("conventions") {
            from(files("../conventions.versions.toml"))
        }
        create("ktor") {
            from("io.ktor:ktor-version-catalog:3.3.2")
        }
    }
}
