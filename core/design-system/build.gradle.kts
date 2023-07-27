plugins {
    id("xyz.dussim.android-library-compose-convention")
}

android {
    namespace = "xyz.dussim.designsystem"

    kotlin {
        //todo
        explicitApiWarning()
    }
}

dependencies {
    api("androidx.compose.ui:ui")
    api("androidx.compose.ui:ui-graphics")
    api("androidx.compose.foundation:foundation")
    api("androidx.compose.runtime:runtime:1.5.0-beta03")

    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3-window-size-class")

    implementation("androidx.window:window:1.1.0")
}