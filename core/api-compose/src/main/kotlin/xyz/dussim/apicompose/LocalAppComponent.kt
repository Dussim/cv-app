package xyz.dussim.apicompose

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import xyz.dussim.api.components.AppComponent

val LocalAppComponent: ProvidableCompositionLocal<AppComponent> =
    staticCompositionLocalOf {
        error("AppComponent is not provided")
    }
