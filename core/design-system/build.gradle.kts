plugins {
    alias(conventions.plugins.xyz.dussim.android.library.compose.convention)
}

android {
    namespace = "xyz.dussim.designsystem"
}

dependencies {
    implementation(libs.androidx.compose.animation.core)
    implementation(libs.androidx.window)
}

tasks.createModuleGraph {
    enabled = false
}
