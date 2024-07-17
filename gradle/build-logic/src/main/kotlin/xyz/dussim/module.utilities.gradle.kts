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

dependencies {
    ktlintRuleset("com.twitter.compose.rules:ktlint:0.0.26")
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
    additionalEditorconfig = mapOf(
        "ktlint_function_naming_ignore_when_annotated_with" to "Composable"
    )

    reporters {
        reporter(ReporterType.HTML)
    }
}