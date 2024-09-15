import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions

plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        create("versioning") {
            id = "xyz.dussim.versioning"
            implementationClass = "xyz.dussim.buildlogic.VersioningPlugin"
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin.target.compilations.configureEach {
    compileTaskProvider.configure {
        compilerOptions {
            (this as KotlinJvmCompilerOptions).jvmTarget.set(JvmTarget.JVM_17)
            freeCompilerArgs.add("-Xjdk-release=17")
        }
    }
}

group = "xyz.dussim"
version = "1.0.0"