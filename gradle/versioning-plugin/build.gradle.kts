import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("versioning") {
            id = "xyz.dussim.versioning"
            implementationClass = "xyz.dussim.buildlogic.VersioningPlugin"
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin.compilerOptions {
    jvmTarget.set(JvmTarget.JVM_17)
    freeCompilerArgs.add("-Xjdk-release=17")
}

group = "xyz.dussim"
version = "1.0.0"