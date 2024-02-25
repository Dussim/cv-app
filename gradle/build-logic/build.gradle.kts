plugins {
    `kotlin-dsl`
}

dependencies {
    implementation("com.android.tools.build:gradle:8.2.2")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.0-Beta4")
    implementation("org.jetbrains.kotlin:kotlin-serialization:2.0.0-Beta4")
    implementation("org.jetbrains.kotlin:kotlin-allopen:2.0.0-Beta4")
    implementation("dev.iurysouza:modulegraph:0.5.0")

    implementation("org.jlleitschuh.gradle:ktlint-gradle:12.1.0")

    implementation("xyz.dussim:versioning-plugin:1.0.0")
}