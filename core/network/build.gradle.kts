plugins {
    id("xyz.dussim.android.library.convention")
}

android {
    namespace = "xyz.dussim.network"
}

dependencies {
    api(project(":core:api"))
    val ktorVersion = "3.1.2"

    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-client-resources:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.8.1")

    api("xyz.dussim:data-model")
}
