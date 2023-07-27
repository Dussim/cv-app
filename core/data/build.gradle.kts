plugins {
    id("xyz.dussim.android-library-convention")
}

android {
    namespace = "xyz.dussim.data"
}

dependencies {
    api(project(":core:resources"))
    api("androidx.annotation:annotation:1.6.0")
}