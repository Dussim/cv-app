plugins {
    id("xyz.dussim.android.library.compose.convention")
}

android {
    namespace = "xyz.dussim.navigation"
}

dependencies {
    api(project(":core:data"))

    api(libs.voyager.core)
}
