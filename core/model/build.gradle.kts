plugins {
    id("xyz.dussim.android.library.convention")
}

android {
    namespace = "xyz.dussim.model"

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    api(project(":core:api"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")

    // Kotest
    testImplementation("io.kotest:kotest-runner-junit5:6.0.0.M3")
    testImplementation("io.kotest:kotest-assertions-core:6.0.0.M3")
    testImplementation("io.kotest:kotest-property:6.0.0.M3")

    // Coroutines test
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
