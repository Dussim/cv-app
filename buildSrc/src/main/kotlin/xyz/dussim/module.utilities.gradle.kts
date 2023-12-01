package xyz.dussim

import dev.iurysouza.modulegraph.LinkText
import dev.iurysouza.modulegraph.Orientation
import dev.iurysouza.modulegraph.Theme

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
}