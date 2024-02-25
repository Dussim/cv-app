plugins {
    id("xyz.dussim.android.library.convention")
}

android {
    namespace = "xyz.dussim.local"
}

dependencies {
    api(project(":core:api"))
}
