plugins {
    id("xyz.dussim.android.library.convention")
}

android {
    namespace = "xyz.dussim.model"
}

dependencies {
    api(project(":core:api"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
}
