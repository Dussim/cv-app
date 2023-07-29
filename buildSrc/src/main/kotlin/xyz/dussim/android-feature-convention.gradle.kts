package xyz.dussim

plugins {
    id("xyz.dussim.android-library-convention")
}

dependencies {
    implementation(project(":core:api"))
    implementation(project(":core:resources"))
}