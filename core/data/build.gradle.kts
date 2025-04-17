plugins {
    id("xyz.dussim.android.library.convention")
}

android {
    namespace = "xyz.dussim.data"
}

dependencies {
    api(libs.androidx.annotation)

    api(libs.xyz.data.model)
}

tasks.createModuleGraph {
    enabled = false
}
