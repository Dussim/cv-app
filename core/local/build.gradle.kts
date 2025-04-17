plugins {
    alias(conventions.plugins.xyz.dussim.android.library.convention)
}

android {
    namespace = "xyz.dussim.local"
}

dependencies {
    api(projects.core.api)

    api(libs.data.model)
}
