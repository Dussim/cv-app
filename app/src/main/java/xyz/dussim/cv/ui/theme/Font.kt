package xyz.dussim.cv.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xyz.dussim.cv.R

val ClashDisplayFontFamily = FontFamily(
    Font(R.font.clash_display_extralight, weight = FontWeight.ExtraLight),
    Font(R.font.clash_display_light, weight = FontWeight.Light),
    Font(R.font.clash_display_regular, weight = FontWeight.Normal),
    Font(R.font.clash_display_medium, weight = FontWeight.Medium),
    Font(R.font.clash_display_semibold, weight = FontWeight.SemiBold),
    Font(R.font.clash_display_bold, weight = FontWeight.Bold),
)

val SatoshiFontFamily = FontFamily(
    Font(R.font.satoshi_light, weight = FontWeight.Light),
    Font(R.font.satoshi_regular, weight = FontWeight.Normal),
    Font(R.font.satoshi_medium, weight = FontWeight.Medium),
    Font(R.font.satoshi_bold, weight = FontWeight.Bold),
)

val H1 = TextStyle.Default.copy(
    fontWeight = FontWeight.Medium,
    fontFamily = ClashDisplayFontFamily,
    fontSize = 30.sp,
    color = Color.White
)

val H2 = TextStyle.Default.copy(
    fontWeight = FontWeight.Medium,
    fontFamily = ClashDisplayFontFamily,
    fontSize = 22.sp,
    color = Color.White
)

val H3 = TextStyle.Default.copy(
    fontWeight = FontWeight.Medium,
    fontFamily = ClashDisplayFontFamily,
    fontSize = 18.sp,
    color = Color.White
)

val Body1 = TextStyle.Default.copy(
    fontWeight = FontWeight.Light,
    fontFamily = SatoshiFontFamily,
    fontSize = 18.sp,
    color = Color.White
)

val Body2 = TextStyle.Default.copy(
    fontWeight = FontWeight.Light,
    fontFamily = SatoshiFontFamily,
    fontSize = 16.sp,
    color = Color.White
)

val Caption = TextStyle.Default.copy(
    fontWeight = FontWeight.Medium,
    fontFamily = SatoshiFontFamily,
    fontSize = 14.sp,
    color = Color.White
)

val Label = TextStyle.Default.copy(
    fontWeight = FontWeight.Medium,
    fontFamily = SatoshiFontFamily,
    fontSize = 12.sp,
    color = Color.White
)

@Preview(name = "App typography")
@Composable
private fun PreviewTextStyles() {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp), modifier = Modifier.fillMaxWidth()) {
        BasicText(text = "H1", style = H1)
        BasicText(text = "H2", style = H2)
        BasicText(text = "H3", style = H3)
        BasicText(text = "Body 1", style = Body1)
        BasicText(text = "Body 2", style = Body2)
        BasicText(text = "Caption", style = Caption)
        BasicText(text = "Label", style = Label)
    }
}