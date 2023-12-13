package xyz.dussim.cv

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import xyz.dussim.api.components.AppComponentHolder
import xyz.dussim.feature.cvcontent.cvContentScreenModule
import xyz.dussim.feature.eastereggs.gym.gymStatsModule
import xyz.dussim.feature.splashscreen.splashScreenModule

/**
 * This is not mistake, that is application with a constructor. See [CvAppComponentFactory].
 * */
class CvApplication(
    appComponentHolder: AppComponentHolder,
) : Application(), AppComponentHolder by appComponentHolder {
    override fun onCreate() {
        super.onCreate()

        ScreenRegistry {
            splashScreenModule()
            cvContentScreenModule()
            gymStatsModule()
        }
    }
}
