package xyz.dussim.cv

import android.os.Bundle
import androidx.activity.ComponentActivity

class TestActivity : ComponentActivity(), ActivityExtensions by ActivityExtensionsImpl() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreenAndWaitUntilLoaded()
        super.onCreate(savedInstanceState)

        loadAppComposableContent()
    }
}
