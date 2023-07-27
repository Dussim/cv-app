dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

//Layer 1 - Modules that are not dependent on any other project module
include(":core:resources")
include(":core:design-system")

//Layer 2 - Modules that are dependent on Layer 1 modules
include(":core:data")
include(":core:api")
include(":core:network")
include(":core:local")

//Layer 3 - Modules that are dependent on previous layers
include(":core:model")

//Top layer - Top level modules that are dependent on previous layers
include(":app")
