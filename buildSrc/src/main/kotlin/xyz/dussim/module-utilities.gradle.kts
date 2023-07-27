package xyz.dussim

import dev.iurysouza.modulegraph.Orientation
import dev.iurysouza.modulegraph.Theme
import dev.iurysouza.modulegraph.LinkText

plugins{
    id("dev.iurysouza.modulegraph")
}

moduleGraphConfig {
    readmePath = "./README.md"
    heading = "# Dependency Diagram"
    orientation = Orientation.TOP_TO_BOTTOM
    theme = Theme.DARK
    linkText = LinkText.CONFIGURATION
}