package xyz.dussim.apicompose

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import xyz.dussim.api.components.ActivityComponentHolder

val LocalActivityComponentHolder: ProvidableCompositionLocal<ActivityComponentHolder> =
    staticCompositionLocalOf {
        error("AppComponentHolder is not provided")
    }
