plugins {
    alias(conventions.plugins.xyz.dussim.android.library.compose.convention)
}

android {
    namespace = "xyz.dussim.ui"
}

dependencies {
    api(projects.core.data)
    api(projects.core.designSystem)
}
