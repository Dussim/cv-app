package xyz.dussim.designsystem

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val AccentColor: Color = Color(0xFF466FFF)
val CardBackgroundColor: Color = Color(0x1A8997CB)

val TextAlternative: Color = Color(0xFFB4BACF)

val DisabledColor: Color = Color(0x1C7F8BBE)

val BackgroundLightColor: Color = Color(0xFF162A6C)
val BackgroundMediumColor: Color = Color(0xFF16265B)
val BackgroundDarkColor: Color = Color(0xFF111737)

val BackgroundBlurBrush: Brush =
    Brush.linearGradient(
        0f to BackgroundDarkColor,
        0.5f to BackgroundLightColor,
        1f to BackgroundMediumColor,
    )
