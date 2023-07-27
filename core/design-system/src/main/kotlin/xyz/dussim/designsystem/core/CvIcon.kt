package xyz.dussim.designsystem.core

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp

@Composable
fun CvIcon(
    @DrawableRes vectorRes: Int,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    CvIcon(
        painter = rememberVectorPainter(image = ImageVector.vectorResource(vectorRes)),
        modifier = modifier,
        contentDescription = contentDescription
    )
}

@Composable
fun CvIcon(
    painter: Painter,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = Modifier
            .size(24.dp)
            .then(modifier)
    )
}