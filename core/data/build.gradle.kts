plugins {
    alias(conventions.plugins.xyz.dussim.android.library.convention)
}

android {
    namespace = "xyz.dussim.data"
}

dependencies {
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.annotation)
    api(libs.androidx.compose.runtime)

    api(libs.data.model)
}

tasks.createModuleGraph {
    enabled = false
}
