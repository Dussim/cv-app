package xyz.dussim

import dev.iurysouza.modulegraph.LinkText
import dev.iurysouza.modulegraph.Orientation
import dev.iurysouza.modulegraph.Theme
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    id("dev.iurysouza.modulegraph")
    id("org.jlleitschuh.gradle.ktlint")
}

moduleGraphConfig {
    readmePath = "./README.md"
    heading = "# Dependency Diagram"
    orientation = Orientation.TOP_TO_BOTTOM
    theme = Theme.DARK
    linkText = LinkText.CONFIGURATION

    excludedConfigurationsRegex = ".*[Tt]est.*"
    setStyleByModuleType = true
    showFullPath = true
}

configure<KtlintExtension> {
    ignoreFailures = true

    reporters {
        reporter(ReporterType.HTML)
    }
}