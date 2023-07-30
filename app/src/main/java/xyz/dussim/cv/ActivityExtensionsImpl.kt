package xyz.dussim.cv

import android.app.Activity
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
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import xyz.dussim.api.components.AppComponentHolder
import xyz.dussim.apicompose.LocalAppComponent
import xyz.dussim.apicompose.LocalAppComponentHolder
import xyz.dussim.designsystem.LocalScreenWidthClass
import xyz.dussim.designsystem.ScreenWidthClass
import xyz.dussim.designsystem.core.ContentBox
import xyz.dussim.navigation.CvAppScreens

internal class ActivityExtensionsImpl : ActivityExtensions {
    private var loaded: Boolean = false
    override fun Activity.installSplashScreenAndWaitUntilLoaded() {
        installSplashScreen().apply {
            setKeepOnScreenCondition { !loaded }
        }
    }

    override fun ComponentActivity.loadAppComposableContent() {
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val appComponentHolder = application as AppComponentHolder
        val appComponent = appComponentHolder.appComponent

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
                LocalScreenWidthClass.provides(screenWidthClass),
                LocalAppComponent.provides(appComponent)
            ) {
                ContentBox(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Navigator(splash) {
                        loaded = it.lastItem != splash
                        CurrentScreen()
                    }
                }
            }
        }
    }
}
