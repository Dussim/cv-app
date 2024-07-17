package xyz.dussim.feature.eastereggs.gym

import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.registry.screenModule
import xyz.dussim.navigation.CvAppScreens

val gymStatsModule: ScreenRegistry.() -> Unit =
    screenModule {
        register<CvAppScreens.GymStats> { GymStatsLoadingScreen }
    }
