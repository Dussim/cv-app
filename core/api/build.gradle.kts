plugins {
    alias(conventions.plugins.xyz.dussim.android.library.convention)
}

android {
    namespace = "xyz.dussim.api"
}

dependencies {
    api(projects.core.data)

    api(libs.kotlinx.coroutines.core)

    api(libs.dussim.data.model)
}
