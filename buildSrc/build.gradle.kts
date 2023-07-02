plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

dependencies {
    implementation("com.android.application:com.android.application.gradle.plugin:8.0.2")
    implementation("com.android.library:com.android.library.gradle.plugin:8.0.2")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21")
}

gradlePlugin {
    plugins {
        create("versioningPlugin") {
            id = "xyz.dussim.versioning"
            implementationClass = "xyz.dussim.OpinionatedVersioningPlugin"
        }
    }
}