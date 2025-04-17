plugins {
    alias(conventions.plugins.xyz.dussim.android.feature.compose.convention)
}

android {
    namespace = "xyz.dussim.feature.cvcontent"
}

dependencies {
    implementation(projects.core.apiCompose)

    implementation(projects.core.ui)
}
