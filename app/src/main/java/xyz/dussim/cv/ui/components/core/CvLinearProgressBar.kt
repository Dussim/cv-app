package xyz.dussim.cv.ui.components.core

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.unit.Dp
import xyz.dussim.cv.ui.theme.AccentColor
import xyz.dussim.cv.ui.theme.DisabledColor

@Composable
fun CvLinearProgressBar(
    cornerRadius: Dp,
    fraction: Float,
    modifier: Modifier = Modifier
) {
    val r = cornerRadius.value
    val corners = CornerRadius(r, r)
    Canvas(modifier = modifier) {
        drawRoundRect(DisabledColor, size = size, cornerRadius = corners)
        drawRoundRect(AccentColor, size = size.copy(width = size.width * fraction), cornerRadius = corners)
    }
}