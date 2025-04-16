plugins {
    id("xyz.dussim.android.library.convention")
}

android {
    namespace = "xyz.dussim.api"
}

dependencies {
    api(project(":core:data"))

    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")

    api("xyz.dussim:data-model")
}
