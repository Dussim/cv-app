plugins {
    alias(conventions.plugins.xyz.dussim.ktor.app.convention)
    alias(libs.plugins.docker.java.application)
}

application {
    mainClass = "xyz.dussim.backend.ApplicationKt"
}

docker {
    javaApplication {
        baseImage = "bellsoft/liberica-runtime-container:jre-21-slim-musl"
        maintainer = "Artur Tuzim `artur@tuzim.xyz`"
        ports = listOf(80)
        images = listOf("dussim/cv-api:latest", "dussim/cv-api:1.5.0")
    }
}

dependencies {
    implementation(libs.data.model)
}
