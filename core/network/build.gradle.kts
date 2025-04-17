plugins {
    id("xyz.dussim.android.library.convention")
}

android {
    namespace = "xyz.dussim.network"
}

dependencies {
    api(project(":core:api"))
    val ktorVersion = "3.1.2"

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.resources)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.ktor.serialization.kotlinx.json)

    implementation(libs.kotlinx.serialization.core)

    api(libs.data.model)
}
