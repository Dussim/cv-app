plugins {
    id("xyz.dussim.android.library.convention")
}

android {
    namespace = "xyz.dussim.data"
}

dependencies {
    api("androidx.annotation:annotation:1.9.0")

    api("xyz.dussim:data-model")
}

tasks.createModuleGraph {
    enabled = false
}
