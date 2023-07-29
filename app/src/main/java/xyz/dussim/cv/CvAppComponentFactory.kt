package xyz.dussim.cv

import android.app.Application
import androidx.core.app.AppComponentFactory

class CvAppComponentFactory : AppComponentFactory() {
    override fun instantiateApplicationCompat(cl: ClassLoader, className: String): Application {
        return CvApplication(LazyAppComponentHolder)
    }
}
