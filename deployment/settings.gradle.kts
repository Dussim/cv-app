enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

pluginManagement {
    includeBuild("../gradle/build-logic")
    includeBuild("../gradle/build-parameters")
    repositories.gradlePluginPortal()
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories.mavenCentral()

    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
        create("conventions") {
            from(files("../gradle/conventions.versions.toml"))
        }
    }
}

includeBuild("../backend")
