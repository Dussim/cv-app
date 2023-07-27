plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

dependencies {
    implementation("com.android.application:com.android.application.gradle.plugin:8.0.2")
    implementation("com.android.library:com.android.library.gradle.plugin:8.0.2")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
    implementation("org.jetbrains.kotlin:kotlin-serialization:1.9.0")
    implementation("dev.iurysouza:modulegraph:0.4.0")
}

gradlePlugin {
    plugins {
        create("versioning") {
            id = "xyz.dussim.versioning"
            implementationClass = "xyz.dussim.buildlogic.VersioningPlugin"
        }
    }
}