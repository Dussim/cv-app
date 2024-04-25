pluginManagement {
    includeBuild("../gradle/build-logic")
    includeBuild("../gradle/build-parameters")
    repositories.gradlePluginPortal()
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories.mavenCentral()
}

includeBuild("../backend")
