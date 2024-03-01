package xyz.dussim.cv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import xyz.dussim.api.components.ActivityComponent
import xyz.dussim.api.components.ActivityComponentHolder
import xyz.dussim.api.components.AppComponentHolder
import xyz.dussim.apicompose.LocalActivityComponent
import xyz.dussim.apicompose.LocalActivityComponentHolder
import xyz.dussim.apicompose.LocalAppComponent
import xyz.dussim.apicompose.LocalAppComponentHolder
import xyz.dussim.designsystem.LocalScreenWidthClass
import xyz.dussim.designsystem.ScreenWidthClass
import xyz.dussim.designsystem.core.ContentBox
import xyz.dussim.navigation.CvAppScreens

class MainActivity(
    private val appComponentHolder: AppComponentHolder
) : ComponentActivity(), ActivityComponentHolder {
    private var loaded: Boolean = false

    override val activityComponent: ActivityComponent by lazy {
        ActivityComponent.create(
            context = this,
            appComponent = appComponentHolder.appComponent
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreenAndWaitUntilLoaded()
        super.onCreate(savedInstanceState)

        loadAppComposableContent()
    }

    private fun installSplashScreenAndWaitUntilLoaded() {
        installSplashScreen().apply {
            setKeepOnScreenCondition { !loaded }
        }
    }

    private fun loadAppComposableContent() {
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val screenWidthClass = ScreenWidthClass.calculateFor(activity = this)
            val uiController = rememberSystemUiController()

            DisposableEffect(uiController) {
                uiController.setSystemBarsColor(Color.Transparent)
                onDispose { }
            }

            val splash = rememberScreen(CvAppScreens.Splash)

            CompositionLocalProvider(
                LocalAppComponentHolder.provides(appComponentHolder),
                LocalAppComponent.provides(appComponentHolder.appComponent),
                LocalActivityComponentHolder.provides(this),
                LocalActivityComponent.provides(activityComponent),
                LocalScreenWidthClass.provides(screenWidthClass),
            ) {
                ContentBox(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Navigator(splash) { navigator ->
                        loaded = navigator.lastItem != splash
                        FadeTransition(navigator)
                    }
                }
            }
        }
    }
}
