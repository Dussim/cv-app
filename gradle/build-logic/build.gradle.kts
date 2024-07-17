import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    `kotlin-dsl`
    id("com.github.ben-manes.versions").version("0.51.0")
}

dependencies {
    implementation("com.android.tools.build:gradle:8.5.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.0")
    implementation("org.jetbrains.kotlin:kotlin-serialization:2.0.0")
    implementation("org.jetbrains.kotlin:compose-compiler-gradle-plugin:2.0.0")
    implementation("dev.iurysouza:modulegraph:0.10.0")

    implementation("org.jlleitschuh.gradle:ktlint-gradle:12.1.1")

    implementation("xyz.dussim:versioning-plugin:1.0.0")
}

version = "1.0.0"

tasks.withType<DependencyUpdatesTask> {
    checkForGradleUpdate = true
    gradleReleaseChannel = "current"
    reportfileName = "build-logic-dependency-updates-report"
    outputDir = layout.projectDirectory.dir(".reports/versions").asFile.path

    filterConfigurations = Spec {
        !it.isCanBeConsumed
    }

    rejectVersionIf {
        candidate.version.isNonStable() && !currentVersion.isNonStable()
    }

    outputFormatter = "html,json"
}

fun String.isNonStable(): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(this)
    return !isStable
}