plugins {
    alias(conventions.plugins.xyz.dussim.android.library.compose.convention)
}

android {
    namespace = "xyz.dussim.apicompose"
}

dependencies {
    api(projects.core.api)
}
