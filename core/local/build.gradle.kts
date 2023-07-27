plugins {
    id("xyz.dussim.android-library-convention")
}

android {
    namespace = "xyz.dussim.local"

    kotlin {
        explicitApiWarning()
    }
}

dependencies {
    api(project(":core:api"))
    api(project(":core:resources"))
}