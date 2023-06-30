package xyz.dussim.cv.ui.components.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.dussim.cv.data.CvDatePattern
import xyz.dussim.cv.ui.theme.Caption
import xyz.dussim.cv.ui.theme.TextAlternative
import java.time.YearMonth


@Composable
fun CvDateChip(
    text: String,
    modifier: Modifier = Modifier
) {
    BasicText(
        text = text,
        style = Caption.copy(color = TextAlternative, textAlign = TextAlign.Center),
        modifier = modifier
            .background(Color(0x1C7F8BBE), RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
    )
}

@Preview
@Composable
private fun PreviewCvDateChip() {
    CvDateChip(text = CvDatePattern.format(YearMonth.now()))
}