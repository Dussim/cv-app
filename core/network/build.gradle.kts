plugins {
    id("xyz.dussim.android.library.convention")
}

android {
    namespace = "xyz.dussim.network"
}

dependencies {
    api(project(":core:api"))
    val ktorVersion = "3.0.0"

    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-client-resources:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.7.3")

    api("xyz.dussim:data-model")
}
