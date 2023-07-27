package xyz.dussim.designsystem.core

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import xyz.dussim.designsystem.AccentColor
import xyz.dussim.designsystem.DisabledColor

public object CvButtonDefaults {

    private val ButtonHorizontalPadding = 16.dp
    private val ButtonVerticalPadding = 16.dp

    private val OutlinedButtonHorizontalPadding = 16.dp
    private val OutlinedButtonVerticalPadding = 8.dp

    public val ContentPadding: PaddingValues =
        PaddingValues(
            start = ButtonHorizontalPadding,
            top = ButtonVerticalPadding,
            end = ButtonHorizontalPadding,
            bottom = ButtonVerticalPadding
        )

    val OutlinedContentPadding =
        PaddingValues(
            start = OutlinedButtonHorizontalPadding,
            top = OutlinedButtonVerticalPadding,
            end = OutlinedButtonHorizontalPadding,
            bottom = OutlinedButtonVerticalPadding
        )

    val ButtonColors: CvButtonColors =
        CvButtonColorsImpl(
            enabledColor = AccentColor,
            disabledColor = DisabledColor
        )

    val OutlinedButtonColors: CvOutlinedButtonColors =
        CvOutlinedButtonsColorsImpl(
            enabledColor = Color.Transparent,
            disabledColor = Color.Transparent,
            enabledOutlineColor = AccentColor,
            disabledOutlineColor = DisabledColor
        )

    val Shape = RoundedCornerShape(64.dp)

    val OutlinedShape = RoundedCornerShape(8.dp)

    @Composable
    fun outlineBorderStroke(enabled: Boolean): BorderStroke {
        val color by OutlinedButtonColors.outlineColor(enabled)
        return BorderStroke(2.dp, color)
    }
}

object CvToggleButtonDefaults {
    private val ToggleButtonHorizontalPadding = 16.dp
    private val ToggleButtonVerticalPadding = 8.dp

    val ContentPadding =
        PaddingValues(
            start = ToggleButtonHorizontalPadding,
            top = ToggleButtonVerticalPadding,
            end = ToggleButtonHorizontalPadding,
            bottom = ToggleButtonVerticalPadding
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
            disabledUncheckedOutlineColor = Color.Transparent
        )

    @Composable
    fun outlineBorderStroke(enabled: Boolean, checked: Boolean): BorderStroke {
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
    override val disabledColor: Color
) : CvButtonColors {

    @Composable
    override fun color(enabled: Boolean): State<Color> {
        return rememberUpdatedState(
            newValue = when (enabled) {
                true -> enabledColor
                false -> disabledColor
            }
        )
    }
}

@Immutable
private data class CvOutlinedButtonsColorsImpl(
    override val enabledOutlineColor: Color,
    override val disabledOutlineColor: Color,
    private val cvButtonColors: CvButtonColors
) : CvOutlinedButtonColors, CvButtonColors by cvButtonColors {

    constructor(
        enabledColor: Color,
        disabledColor: Color,
        enabledOutlineColor: Color,
        disabledOutlineColor: Color
    ) : this(
        enabledOutlineColor = enabledOutlineColor,
        disabledOutlineColor = disabledOutlineColor,
        cvButtonColors = CvButtonColorsImpl(enabledColor, disabledColor)
    )

    @Composable
    override fun outlineColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(
            newValue = when (enabled) {
                true -> enabledOutlineColor
                false -> disabledOutlineColor
            }
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
        checked: Boolean
    ): State<Color> {
        return rememberUpdatedState(
            newValue = when {
                enabled && checked -> enabledCheckedColor
                enabled && !checked -> enabledUncheckedColor
                !enabled && checked -> disabledCheckedColor
                else -> disabledUncheckedColor
            }
        )
    }

    @Composable
    fun outlineColor(
        enabled: Boolean,
        checked: Boolean
    ): State<Color> {
        return rememberUpdatedState(
            newValue = when {
                enabled && checked -> enabledCheckedOutlineColor
                enabled && !checked -> enabledCheckedOutlineColor
                !enabled && checked -> disabledCheckedOutlineColor
                else -> disabledUncheckedOutlineColor
            }
        )
    }

    @Composable
    fun outlineColorValue(enabled: Boolean, checked: Boolean) = outlineColor(enabled = enabled, checked = checked).value
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
    override val disabledUncheckedOutlineColor: Color
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
    interactionSource: MutableInteractionSource = remember {
        MutableInteractionSource()
    },
    content: @Composable RowScope.() -> Unit
) {
    val background by colors.color(enabled = enabled)

    Box(
        modifier = modifier
            .then(
                when (border) {
                    null -> Modifier
                    else -> Modifier.border(border, shape)
                }
            )
            .background(background, shape)
            .clip(shape)
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(), // FIXME, move outside
                role = Role.Button,
                enabled = enabled,
                onClick = onClick
            )
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(contentPadding),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
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
    interactionSource: MutableInteractionSource = remember {
        MutableInteractionSource()
    },
    content: @Composable RowScope.() -> Unit
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
        content = content
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
    interactionSource: MutableInteractionSource = remember {
        MutableInteractionSource()
    },
    content: @Composable RowScope.() -> Unit
) {
    val background by colors.backgroundColor(enabled = enabled, checked = checked)

    Box(
        modifier = modifier
            .then(
                when (border) {
                    null -> Modifier
                    else -> Modifier.border(border, shape)
                }
            )
            .background(background, shape)
            .clip(shape)
            .toggleable(
                interactionSource = interactionSource,
                indication = rememberRipple(),
                value = checked,
                enabled = enabled,
                role = Role.Switch,
                onValueChange = onCheckedChange
            )
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(contentPadding),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            content()
        }
    }
}
//
//
//@Composable
//private fun PreviewCvButton() {
//    Column {
//        CvButton(onClick = { }) {
//            ButtonPreviewContent()
//        }
//    }
//}
//
//
//@Composable
//private fun PreviewCvButtonDisabled() {
//    CvButton(onClick = { }, enabled = false) {
//        ButtonPreviewContent()
//    }
//}
//
//
//@Composable
//private fun PreviewCvOutlinedButton() {
//    CvOutlinedButton(onClick = { }) {
//        ButtonPreviewContent()
//    }
//}
//
//
//@Composable
//private fun PreviewCvOutlinedButtonDisabled() {
//    CvOutlinedButton(onClick = { }, enabled = false) {
//        ButtonPreviewContent()
//    }
//}
//
//
//@Composable
//private fun PreviewOutlinedButNormalSizeButtonEnabled() {
//    CvOutlinedButton(
//        onClick = { },
//        shape = CvButtonDefaults.Shape,
//        contentPadding = CvButtonDefaults.ContentPadding
//    ) {
//        ButtonPreviewContent()
//    }
//}
//
//
//@Composable
//private fun PreviewToggleStates() {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        listOf(true to true, true to false, false to true, false to false).forEach { (checked, enabled) ->
//            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//                BasicText(text = "Checked = { $checked }, Enabled = { $enabled }", style = xyz.dussim.designsystem.Label)
//                CVOutlinedToggleButton(checked = checked, onCheckedChange = {}, enabled = enabled) {
//                    ButtonPreviewContent()
//                }
//            }
//
//        }
//    }
//}
//
//
//@Composable
//private fun PreviewOutlinedToggle() {
//    val (checked, setChecked) = remember {
//        mutableStateOf(false)
//    }
//
//    CVOutlinedToggleButton(checked = checked, onCheckedChange = setChecked) {
//        ButtonPreviewContent()
//    }
//}
//
//@Composable
//private fun ButtonPreviewContent() {
//    BasicText(text = "Download PDF", style = xyz.dussim.designsystem.H3)
//    HorizontalSpacer()
//    CvIcon(vectorRes = R.drawable.download)
//}
//
//@Composable
//private fun HorizontalSpacer() {
//    Spacer(modifier = Modifier.width(10.dp))
//}

