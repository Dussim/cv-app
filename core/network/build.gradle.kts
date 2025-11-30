plugins {
    alias(conventions.plugins.xyz.dussim.android.library.convention)
}

android {
    namespace = "xyz.dussim.network"
}

dependencies {
    api(projects.core.api)

    implementation(ktor.client.core)
    implementation(ktor.client.android)
    implementation(ktor.client.contentNegotiation)
    implementation(ktor.client.resources)
    implementation(ktor.serialization.kotlinx.json)

    api(libs.data.model)
}
