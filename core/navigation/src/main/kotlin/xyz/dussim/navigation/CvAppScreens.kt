package xyz.dussim.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider
import xyz.dussim.data.CvData

sealed interface CvAppScreens : ScreenProvider {
    data object Splash : CvAppScreens

    data class CvContent(val cvData: CvData) : CvAppScreens

    data object GymStats : CvAppScreens
}
