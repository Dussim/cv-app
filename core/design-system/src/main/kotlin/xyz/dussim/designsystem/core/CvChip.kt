package xyz.dussim.designsystem.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import xyz.dussim.designsystem.*


@Deprecated("Use CvChip instead")
@Composable
fun DeprecatedCvChip(
    text: String,
    modifier: Modifier = Modifier
) {
    CvChip(modifier = modifier) {
        BasicText(
            text = text,
            style = Caption.copy(TextAlternative),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

public data object CvChipDefaults {
    private val CvChipHorizontalPadding = margin_1x
    private val CvChipVerticalPadding = margin_0_5x

    val ContentPadding: PaddingValues =
        PaddingValues(
            horizontal = CvChipHorizontalPadding,
            vertical = CvChipVerticalPadding
        )

    val CvChipColors: CvChipColors =
        CvChipColorsImpl(
            background = DisabledColor
        )

    val CvChipShape: Shape = ShapeCorner_1x
}

@Stable
public interface CvChipColors {
    public val background: Color
}

@Immutable
internal class CvChipColorsImpl(
    override val background: Color
) : CvChipColors

@Composable
public fun CvChip(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = CvChipDefaults.ContentPadding,
    cvChipColors: CvChipColors = CvChipDefaults.CvChipColors,
    shape: Shape = CvChipDefaults.CvChipShape,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .background(cvChipColors.background, shape)
            .padding(contentPadding),
        content = content
    )
}
