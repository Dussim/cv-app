package xyz.dussim.feature.cvcontent

import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.registry.screenModule
import xyz.dussim.navigation.CvAppScreens

val cvContentScreenModule: ScreenRegistry.() -> Unit = screenModule {
    register<CvAppScreens.CvContent> { CvContentScreen(cvData = it.cvData) }
}