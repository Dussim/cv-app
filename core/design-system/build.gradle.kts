plugins {
    alias(conventions.plugins.xyz.dussim.android.library.compose.convention)
}

android {
    namespace = "xyz.dussim.designsystem"
}

dependencies {
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material3.window.size.class1)

    implementation(libs.androidx.window)
}

tasks.createModuleGraph {
    enabled = false
}
