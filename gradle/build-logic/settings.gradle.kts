pluginManagement {
    includeBuild("../versioning-plugin")
    repositories.gradlePluginPortal()
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        exclusiveContent {
            forRepository { maven("https://androidx.dev/storage/compose-compiler/repository/") }
            filter { includeGroup("androidx.compose.compiler") }
        }

        google {
            content {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google.testing")
            }
        }
        gradlePluginPortal()
        mavenCentral()
    }
}