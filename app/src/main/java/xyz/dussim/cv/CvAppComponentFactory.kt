package xyz.dussim.cv

import android.app.Activity
import android.app.Application
import android.content.Intent
import androidx.core.app.AppComponentFactory
import xyz.dussim.api.components.AppComponentHolder

class CvAppComponentFactory : AppComponentFactory() {
    private lateinit var appComponentHolder: AppComponentHolder

    override fun instantiateApplicationCompat(
        cl: ClassLoader,
        className: String,
    ): Application {
        return CvApplication().also { appComponentHolder = it }
    }

    override fun instantiateActivityCompat(
        cl: ClassLoader,
        className: String,
        intent: Intent?,
    ): Activity {
        // By definition of the app, there is only one activity
        return MainActivity(appComponentHolder)
    }
}
