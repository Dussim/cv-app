import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    alias(conventions.plugins.xyz.dussim.module.utilities)
    alias(libs.plugins.gradle.versions)
    alias(libs.plugins.android.test) apply false
    alias(libs.plugins.baselineprofile) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.stability.analyzer) apply false
}

tasks.withType<DependencyUpdatesTask> {
    checkForGradleUpdate = true
    gradleReleaseChannel = "current"
    reportfileName = "app-logic-dependency-updates-report"
    outputDir =
        layout.projectDirectory
            .dir(".reports/versions")
            .asFile.path

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
    gradleVersion = "release-candidate"
}
