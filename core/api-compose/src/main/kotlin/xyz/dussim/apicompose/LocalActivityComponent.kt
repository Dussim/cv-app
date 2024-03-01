package xyz.dussim.apicompose

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import xyz.dussim.api.components.ActivityComponent

val LocalActivityComponent: ProvidableCompositionLocal<ActivityComponent> = staticCompositionLocalOf {
    error("AppComponentHolder is not provided")
}
