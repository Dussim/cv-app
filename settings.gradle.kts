dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

// Layer 1 - Modules that are not dependent on any other project module
include(":core:resources")
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

// Top layer - Top level modules that are dependent on previous layers
include(":app")
include(":backend")
