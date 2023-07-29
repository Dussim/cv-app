package xyz.dussim.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider
import xyz.dussim.data.CvData

public sealed interface CvAppScreens : ScreenProvider {
    public data object Splash : CvAppScreens

    public data class CvContent(val cvData: CvData) : CvAppScreens
}