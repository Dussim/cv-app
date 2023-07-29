plugins {
    id("xyz.dussim.android-library-convention")
    kotlin("plugin.parcelize")
}

android {
    namespace = "xyz.dussim.local"
}

dependencies {
    api(project(":core:api"))
    api(project(":core:resources"))
}
