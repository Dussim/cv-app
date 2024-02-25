package xyz.dussim.cv

import android.app.Activity
import androidx.activity.ComponentActivity

interface ActivityExtensions {
    fun Activity.installSplashScreenAndWaitUntilLoaded()

    fun ComponentActivity.loadAppComposableContent()
}
