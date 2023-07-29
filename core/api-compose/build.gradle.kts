plugins {
    id("xyz.dussim.android-library-compose-convention")
}

android {
    namespace = "xyz.dussim.apicompose"
}

dependencies {
    api(project(":core:api"))
}
