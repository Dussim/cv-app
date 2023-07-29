package xyz.dussim.apicompose

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import xyz.dussim.api.components.AppComponentHolder

public val LocalAppComponentHolder: ProvidableCompositionLocal<AppComponentHolder> = staticCompositionLocalOf {
    error("AppComponentHolder is not provided")
}