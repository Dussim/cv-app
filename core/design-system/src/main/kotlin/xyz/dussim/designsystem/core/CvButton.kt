package xyz.dussim.designsystem.core

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import xyz.dussim.designsystem.AccentColor
import xyz.dussim.designsystem.DisabledColor
import xyz.dussim.designsystem.RoundedCornerShape_1x
import xyz.dussim.designsystem.RoundedCornerShape_8x
import xyz.dussim.designsystem.margin_1x
import xyz.dussim.designsystem.margin_2x

object CvButtonDefaults {
    private val ButtonHorizontalPadding = margin_2x
    private val ButtonVerticalPadding = margin_2x

    private val OutlinedButtonHorizontalPadding = margin_2x
    private val OutlinedButtonVerticalPadding = margin_1x

    val ContentPadding: PaddingValues =
        PaddingValues(
            start = ButtonHorizontalPadding,
            top = ButtonVerticalPadding,
            end = ButtonHorizontalPadding,
            bottom = ButtonVerticalPadding,
        )

    val OutlinedContentPadding =
        PaddingValues(
            start = OutlinedButtonHorizontalPadding,
            top = OutlinedButtonVerticalPadding,
            end = OutlinedButtonHorizontalPadding,
            bottom = OutlinedButtonVerticalPadding,
        )

    val ButtonColors: CvButtonColors =
        CvButtonColorsImpl(
            enabledColor = AccentColor,
            disabledColor = DisabledColor,
        )

    val OutlinedButtonColors: CvOutlinedButtonColors =
        CvOutlinedButtonsColorsImpl(
            enabledColor = Color.Transparent,
            disabledColor = Color.Transparent,
            enabledOutlineColor = AccentColor,
            disabledOutlineColor = DisabledColor,
        )

    val Shape = RoundedCornerShape_8x

    val OutlinedShape = RoundedCornerShape_1x

    @Composable
    fun outlineBorderStroke(enabled: Boolean): BorderStroke {
        val color by OutlinedButtonColors.outlineColor(enabled)
        return BorderStroke(2.dp, color)
    }
}

object CvToggleButtonDefaults {
    private val ToggleButtonHorizontalPadding = margin_2x
    private val ToggleButtonVerticalPadding = margin_1x

    val ContentPadding =
        PaddingValues(
            start = ToggleButtonHorizontalPadding,
            top = ToggleButtonVerticalPadding,
            end = ToggleButtonHorizontalPadding,
            bottom = ToggleButtonVerticalPadding,
        )

    val OutlinedToggleColors: CvOutlinedToggleButtonColors =
        CvOutlinedToggleButtonColorsImpl(
            enabledCheckedColor = AccentColor,
            disabledCheckedColor = DisabledColor,
            enabledUncheckedColor = Color.Transparent,
            disabledUncheckedColor = DisabledColor,
            enabledCheckedOutlineColor = AccentColor,
            disabledCheckedOutlineColor = DisabledColor,
            enabledUncheckedOutlineColor = AccentColor,
            disabledUncheckedOutlineColor = Color.Transparent,
        )

    @Composable
    fun outlineBorderStroke(
        enabled: Boolean,
        checked: Boolean,
    ): BorderStroke {
        return BorderStroke(2.dp, OutlinedToggleColors.outlineColorValue(enabled, checked))
    }
}

@Stable
interface CvButtonColors {
    val enabledColor: Color
    val disabledColor: Color

    @Composable
    fun color(enabled: Boolean): State<Color>
}

@Stable
interface CvOutlinedButtonColors : CvButtonColors {
    val enabledOutlineColor: Color
    val disabledOutlineColor: Color

    @Composable
    fun outlineColor(enabled: Boolean): State<Color>
}

@Immutable
private data class CvButtonColorsImpl(
    override val enabledColor: Color,
    override val disabledColor: Color,
) : CvButtonColors {
    @Composable
    override fun color(enabled: Boolean): State<Color> {
        return rememberUpdatedState(
            newValue =
                when (enabled) {
                    true -> enabledColor
                    false -> disabledColor
                },
        )
    }
}

@Immutable
private data class CvOutlinedButtonsColorsImpl(
    override val enabledOutlineColor: Color,
    override val disabledOutlineColor: Color,
    private val cvButtonColors: CvButtonColors,
) : CvOutlinedButtonColors, CvButtonColors by cvButtonColors {
    constructor(
        enabledColor: Color,
        disabledColor: Color,
        enabledOutlineColor: Color,
        disabledOutlineColor: Color,
    ) : this(
        enabledOutlineColor = enabledOutlineColor,
        disabledOutlineColor = disabledOutlineColor,
        cvButtonColors = CvButtonColorsImpl(enabledColor, disabledColor),
    )

    @Composable
    override fun outlineColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(
            newValue =
                when (enabled) {
                    true -> enabledOutlineColor
                    false -> disabledOutlineColor
                },
        )
    }
}

@Stable
interface CvOutlinedToggleButtonColors {
    val enabledCheckedColor: Color
    val disabledCheckedColor: Color

    val enabledUncheckedColor: Color
    val disabledUncheckedColor: Color

    val enabledCheckedOutlineColor: Color
    val disabledCheckedOutlineColor: Color

    val enabledUncheckedOutlineColor: Color
    val disabledUncheckedOutlineColor: Color

    @Composable
    fun backgroundColor(
        enabled: Boolean,
        checked: Boolean,
    ): State<Color> {
        return rememberUpdatedState(
            newValue =
                when {
                    enabled && checked -> enabledCheckedColor
                    enabled && !checked -> enabledUncheckedColor
                    !enabled && checked -> disabledCheckedColor
                    else -> disabledUncheckedColor
                },
        )
    }

    @Composable
    fun outlineColor(
        enabled: Boolean,
        checked: Boolean,
    ): State<Color> {
        return rememberUpdatedState(
            newValue =
                when {
                    enabled && checked -> enabledCheckedOutlineColor
                    enabled && !checked -> enabledCheckedOutlineColor
                    !enabled && checked -> disabledCheckedOutlineColor
                    else -> disabledUncheckedOutlineColor
                },
        )
    }

    @Composable
    fun outlineColorValue(
        enabled: Boolean,
        checked: Boolean,
    ) = outlineColor(enabled = enabled, checked = checked).value
}

@Immutable
private data class CvOutlinedToggleButtonColorsImpl(
    override val enabledCheckedColor: Color,
    override val disabledCheckedColor: Color,
    override val enabledUncheckedColor: Color,
    override val disabledUncheckedColor: Color,
    override val enabledCheckedOutlineColor: Color,
    override val disabledCheckedOutlineColor: Color,
    override val enabledUncheckedOutlineColor: Color,
    override val disabledUncheckedOutlineColor: Color,
) : CvOutlinedToggleButtonColors

@Composable
fun CvButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = CvButtonDefaults.Shape,
    border: BorderStroke? = null,
    colors: CvButtonColors = CvButtonDefaults.ButtonColors,
    contentPadding: PaddingValues = CvButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource =
        remember {
            MutableInteractionSource()
        },
    content: @Composable RowScope.() -> Unit,
) {
    val background by colors.color(enabled = enabled)

    Box(
        modifier =
            modifier
                .then(
                    when (border) {
                        null -> Modifier
                        else -> Modifier.border(border, shape)
                    },
                )
                .background(background, shape)
                .clip(shape)
                .clickable(
                    interactionSource = interactionSource,
                    indication = ripple(),
                    role = Role.Button,
                    enabled = enabled,
                    onClick = onClick,
                ),
    ) {
        Row(
            modifier =
                Modifier
                    .align(Alignment.Center)
                    .padding(contentPadding),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            content()
        }
    }
}

@Composable
fun CvOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = CvButtonDefaults.OutlinedShape,
    border: BorderStroke? = CvButtonDefaults.outlineBorderStroke(enabled),
    colors: CvOutlinedButtonColors = CvButtonDefaults.OutlinedButtonColors,
    contentPadding: PaddingValues = CvButtonDefaults.OutlinedContentPadding,
    interactionSource: MutableInteractionSource =
        remember {
            MutableInteractionSource()
        },
    content: @Composable RowScope.() -> Unit,
) {
    CvButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content,
    )
}

@Composable
fun CVOutlinedToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = CvButtonDefaults.OutlinedShape,
    border: BorderStroke? = CvToggleButtonDefaults.outlineBorderStroke(enabled, checked),
    colors: CvOutlinedToggleButtonColors = CvToggleButtonDefaults.OutlinedToggleColors,
    contentPadding: PaddingValues = CvToggleButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource =
        remember {
            MutableInteractionSource()
        },
    content: @Composable RowScope.() -> Unit,
) {
    val background by colors.backgroundColor(enabled = enabled, checked = checked)

    Box(
        modifier =
            modifier
                .then(
                    when (border) {
                        null -> Modifier
                        else -> Modifier.border(border, shape)
                    },
                )
                .background(background, shape)
                .clip(shape)
                .toggleable(
                    interactionSource = interactionSource,
                    indication = ripple(),
                    value = checked,
                    enabled = enabled,
                    role = Role.Switch,
                    onValueChange = onCheckedChange,
                ),
    ) {
        Row(
            modifier =
                Modifier
                    .align(Alignment.Center)
                    .padding(contentPadding),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            content()
        }
    }
}
