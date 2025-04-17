plugins {
    alias(conventions.plugins.xyz.dussim.android.library.compose.convention)
}

android {
    namespace = "xyz.dussim.navigation"
}

dependencies {
    api(projects.core.data)

    api(libs.voyager.core)
}
