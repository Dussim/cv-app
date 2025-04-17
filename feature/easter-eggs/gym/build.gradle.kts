plugins {
    id("xyz.dussim.android.feature.compose.convention")
}

android {
    namespace = "xyz.dussim.feature.ee.gym"
}

dependencies {
    implementation(projects.core.apiCompose)

    implementation(projects.core.ui)
}
