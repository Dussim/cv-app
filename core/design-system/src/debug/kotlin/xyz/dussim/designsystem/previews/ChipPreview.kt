package xyz.dussim.designsystem.previews

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import xyz.dussim.designsystem.Caption
import xyz.dussim.designsystem.TextAlternative
import xyz.dussim.designsystem.core.CvChip

@Preview
@Composable
private fun PreviewCvChip() {
    CvChip {
        BasicText(text = "2018-01 – 2019-07", style = Caption.copy(TextAlternative))
    }
}
