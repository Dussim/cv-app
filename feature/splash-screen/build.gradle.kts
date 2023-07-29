plugins {
    id("xyz.dussim.android-feature-compose-convention")
}

android {
    namespace = "xyz.dussim.feature.splashscreen"
}

dependencies {
    implementation(project(":core:api-compose"))
}
