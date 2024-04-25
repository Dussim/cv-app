import buildparameters.BuildParametersExtension

pluginManagement {
    includeBuild("gradle/build-logic")
    includeBuild("gradle/build-parameters")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://androidx.dev/storage/compose-compiler/repository/")
    }
}

plugins {
    id("com.gradle.enterprise").version("3.17.2")
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
)
