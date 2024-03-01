package xyz.dussim.cv

import android.app.Application
import android.content.pm.PackageManager
import cafe.adriel.voyager.core.registry.ScreenRegistry
import xyz.dussim.api.components.AppComponent
import xyz.dussim.api.components.AppComponentHolder
import xyz.dussim.api.components.BaseUrlProvider
import xyz.dussim.api.coroutines.DispatchersComponent
import xyz.dussim.api.coroutines.create
import xyz.dussim.feature.cvcontent.cvContentScreenModule
import xyz.dussim.feature.eastereggs.gym.gymStatsModule
import xyz.dussim.feature.splashscreen.splashScreenModule

/**
 * This is not a mistake, that is application with a constructor. See [CvAppComponentFactory].
 * */
class CvApplication : Application(), AppComponentHolder {
    private val baseUrl by lazy {
        packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA).metaData.run {
            getString("api-url") ?: throw IllegalStateException("api-url is not defined")
        }
    }
    override lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = AppComponentImpl(
            appContext = this,
            baseUrlProvider = { baseUrl },
            dispatchersComponent = DispatchersComponent.create()
        )

        ScreenRegistry {
            splashScreenModule()
            cvContentScreenModule()
            gymStatsModule()
        }
    }

    private data class AppComponentImpl(
        override val appContext: Application,
        override val baseUrlProvider: BaseUrlProvider,
        override val dispatchersComponent: DispatchersComponent
    ) : AppComponent
}
