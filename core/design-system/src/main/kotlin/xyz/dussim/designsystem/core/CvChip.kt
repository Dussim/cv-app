package xyz.dussim.designsystem.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import xyz.dussim.designsystem.DisabledColor
import xyz.dussim.designsystem.RoundedCornerShape_1x
import xyz.dussim.designsystem.margin_0_5x
import xyz.dussim.designsystem.margin_1x

data object CvChipDefaults {
    private val CvChipHorizontalPadding = margin_1x
    private val CvChipVerticalPadding = margin_0_5x

    val ContentPadding: PaddingValues =
        PaddingValues(
            horizontal = CvChipHorizontalPadding,
            vertical = CvChipVerticalPadding,
        )

    val CvChipColors: CvChipColors =
        CvChipColorsImpl(
            background = DisabledColor,
        )

    val CvChipShape: Shape = RoundedCornerShape_1x
}

@Stable interface CvChipColors {
    val background: Color
}

@Immutable
internal class CvChipColorsImpl(
    override val background: Color,
) : CvChipColors

@Composable
fun CvChip(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = CvChipDefaults.ContentPadding,
    cvChipColors: CvChipColors = CvChipDefaults.CvChipColors,
    shape: Shape = CvChipDefaults.CvChipShape,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier =
            modifier
                .background(cvChipColors.background, shape)
                .padding(contentPadding),
        content = content,
    )
}
