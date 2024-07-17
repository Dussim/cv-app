plugins {
    id("xyz.dussim.android.library.convention")
}

android {
    namespace = "xyz.dussim.api"
}

dependencies {
    api(project(":core:data"))

    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")

    api("xyz.dussim:data-model")
}
