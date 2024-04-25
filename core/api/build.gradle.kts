plugins {
    id("xyz.dussim.android.library.convention")
}

android {
    namespace = "xyz.dussim.api"
}

dependencies {
    api(project(":core:data"))

    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")

    api("xyz.dussim:data-model:1.0.0")
}

