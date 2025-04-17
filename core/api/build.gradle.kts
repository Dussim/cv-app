plugins {
    id("xyz.dussim.android.library.convention")
}

android {
    namespace = "xyz.dussim.api"
}

dependencies {
    api(project(":core:data"))

    api(libs.kotlinx.coroutines.core)

    api(libs.dussim.data.model)
}
