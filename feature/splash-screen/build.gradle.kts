plugins {
    alias(conventions.plugins.xyz.dussim.android.feature.compose.convention)
}

android {
    namespace = "xyz.dussim.feature.splashscreen"
}

dependencies {
    implementation(projects.core.apiCompose)
}
