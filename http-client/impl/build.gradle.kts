plugins {
    id("xyz.dussim.kotlin-api-module-convention")
}

dependencies {
    val ktorVersion = "2.3.2"

    api(project(":http-client:api"))

    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
}