plugins {
    id("xyz.dussim.android-feature-compose-convention")
}

android {
    namespace = "xyz.dussim.feature.cvcontent"
}

dependencies {
    implementation(project(":core:design-system"))
    implementation(project(":core:api-compose"))
    implementation(project(":core:resources"))

    api(project(":core:ui"))
}
