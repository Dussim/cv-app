package xyz.dussim.feature.splashscreen

import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.registry.screenModule
import xyz.dussim.navigation.CvAppScreens

val splashScreenModule: ScreenRegistry.() -> Unit =
    screenModule {
        register<CvAppScreens.Splash> { SplashScreen }
    }
