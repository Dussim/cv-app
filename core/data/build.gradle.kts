plugins {
    alias(conventions.plugins.xyz.dussim.android.library.convention)
}

android {
    namespace = "xyz.dussim.data"
}

dependencies {
    api(libs.androidx.annotation)

    api(libs.data.model)
}

tasks.createModuleGraph {
    enabled = false
}
