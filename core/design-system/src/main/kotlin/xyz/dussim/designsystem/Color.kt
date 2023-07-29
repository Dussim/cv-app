package xyz.dussim.designsystem

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

public val AccentColor: Color = Color(0xFF466FFF)
public val CardBackgroundColor: Color = Color(0x1A8997CB)

public val TextAlternative: Color = Color(0xFFB4BACF)

public val DisabledColor: Color = Color(0x1C7F8BBE)

public val BackgroundLightColor: Color = Color(0xFF162A6C)
public val BackgroundMediumColor: Color = Color(0xFF16265B)
public val BackgroundDarkColor: Color = Color(0xFF111737)

public val BackgroundBlurBrush: Brush = Brush.linearGradient(
    0f to BackgroundDarkColor,
    0.5f to BackgroundLightColor,
    1f to BackgroundMediumColor,
)
