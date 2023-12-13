package xyz.dussim

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("xyz.dussim.module.utilities")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
}

kotlin {
    jvmToolchain(21)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}