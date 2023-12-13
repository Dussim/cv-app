import buildparameters.BuildParametersExtension

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://androidx.dev/storage/compose-compiler/repository/")
    }
}
pluginManagement {
    includeBuild("gradle/build-logic")
    includeBuild("gradle/build-parameters")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("com.gradle.enterprise").version("3.16")
    id("xyz.dussim.build-parameters")
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}

buildCache {
    val buildParameters = the<BuildParametersExtension>()
    local {
        isEnabled = !buildParameters.ci
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

// Layer 1 - Modules that are not dependent on any other project module
include(":core:data")
include(":core:design-system")

// Layer 2 - Modules that are dependent on Layer 1 modules
include(":core:ui")
include(":core:api")
include(":core:navigation")

// Layer 3 - Modules that are dependent on previous layers
include(":core:api-compose")
include(":core:network")
include(":core:local")
include(":core:model")

// Features Layer - Modules that are dependent on previous layers and provide chunks of app functionality
include(":feature:splash-screen")
include(":feature:cv-content")

include(":feature:easter-eggs:gym")

// Top layer - Top level modules that are dependent on previous layers
include(":app")
include(":backend")
