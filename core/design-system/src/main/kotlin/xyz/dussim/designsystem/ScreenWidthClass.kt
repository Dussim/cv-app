package xyz.dussim.designsystem

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toComposeRect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.window.layout.WindowMetricsCalculator

/**
 * Opinionated screen width classes for responsive design of this app
 */
enum class ScreenWidthClass {
    Small,
    Medium,
    Big,
    ;

    companion object {
        @Composable
        fun calculateFor(activity: Activity): ScreenWidthClass {
            LocalConfiguration.current
            val density = LocalDensity.current
            val metrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(activity)
            val width = with(density) { metrics.bounds.toComposeRect().size.toDpSize() }.width

            return when {
                width < 768.dp -> Small
                width < 1200.dp -> Medium
                else -> Big
            }
        }
    }
}
