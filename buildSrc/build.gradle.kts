plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

dependencies {
    implementation("com.android.application:com.android.application.gradle.plugin:8.1.4")
    implementation("com.android.library:com.android.library.gradle.plugin:8.1.4")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.0-Beta1")
    implementation("org.jetbrains.kotlin:kotlin-serialization:2.0.0-Beta1")
    implementation("dev.iurysouza:modulegraph:0.4.0")

    implementation("org.jlleitschuh.gradle:ktlint-gradle:11.5.0")
}

gradlePlugin {
    plugins {
        create("versioning") {
            id = "xyz.dussim.versioning"
            implementationClass = "xyz.dussim.buildlogic.VersioningPlugin"
        }
    }
}