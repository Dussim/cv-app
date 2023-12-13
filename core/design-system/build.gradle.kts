plugins {
    id("xyz.dussim.android.library.compose.convention")
}

android {
    namespace = "xyz.dussim.designsystem"
}

dependencies {
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3-window-size-class")

    implementation("androidx.window:window:1.1.0")
}
