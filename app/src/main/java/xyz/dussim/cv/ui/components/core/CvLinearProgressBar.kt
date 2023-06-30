package xyz.dussim.cv.ui.components.core

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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

@Preview
@Composable
fun PreviewCvLinearProgressBar() {
    CvLinearProgressBar(
        cornerRadius = 20.dp,
        fraction = 0.5f,
        modifier = Modifier.size(width = 200.dp, height = 20.dp)
    )
}