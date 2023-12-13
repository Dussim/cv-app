package xyz.dussim.cv

import android.app.Activity
import android.app.Application
import android.content.Intent
import androidx.core.app.AppComponentFactory

class CvAppComponentFactory : AppComponentFactory() {
    private val activityExtensions = ActivityExtensionsImpl()

    override fun instantiateApplicationCompat(
        cl: ClassLoader,
        className: String,
    ): Application {
        return CvApplication(AppComponentHolderImpl)
    }

    override fun instantiateActivityCompat(
        cl: ClassLoader,
        className: String,
        intent: Intent?,
    ): Activity {
        // By definition of the app, there is only one activity
        return MainActivity(activityExtensions)
    }
}
