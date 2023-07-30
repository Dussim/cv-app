package xyz.dussim.cv

import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity(extensions: ActivityExtensions) : ComponentActivity(), ActivityExtensions by extensions {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreenAndWaitUntilLoaded()
        super.onCreate(savedInstanceState)

        loadAppComposableContent()
    }
}
