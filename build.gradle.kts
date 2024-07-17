import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("xyz.dussim.module.utilities")
    id("com.github.ben-manes.versions").version("0.51.0")
}

tasks.withType<DependencyUpdatesTask> {
    checkForGradleUpdate = true
    gradleReleaseChannel = "current"
    reportfileName = "app-logic-dependency-updates-report"
    outputDir = layout.projectDirectory.dir(".reports/versions").asFile.path

    filterConfigurations =
        Spec {
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

tasks.named("dependencyUpdates") {
    dependsOn(gradle.includedBuild("build-logic").task(":dependencyUpdates"))

    doLast {
        copy {
            from(gradle.includedBuild("build-logic").projectDir.resolve(".reports/versions"))
            into(layout.projectDirectory.dir(".reports/versions"))
        }
    }
}

tasks.wrapper {
    distributionType = Wrapper.DistributionType.ALL
}
