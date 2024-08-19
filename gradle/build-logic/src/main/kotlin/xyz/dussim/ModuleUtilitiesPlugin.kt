package xyz.dussim

import dev.iurysouza.modulegraph.LinkText
import dev.iurysouza.modulegraph.Orientation
import dev.iurysouza.modulegraph.Theme
import dev.iurysouza.modulegraph.gradle.ModuleGraphExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

class ModuleUtilitiesPlugin : Plugin<Project> {
    override fun apply(target: Project) = target.run {
        pluginManager.apply("dev.iurysouza.modulegraph")
        pluginManager.apply("org.jlleitschuh.gradle.ktlint")

        dependencies {
            "ktlintRuleset"("com.twitter.compose.rules:ktlint:0.0.26")
        }

        configure<ModuleGraphExtension> {
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
    }
}