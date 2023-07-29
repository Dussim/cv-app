package xyz.dussim.designsystem

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

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