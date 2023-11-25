package xyz.dussim.designsystem.core.modifiers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo

fun Modifier.easterEggClickable(
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    timeSpan: Long = 1000L,
    minimumClicks: Int = 5,
    currentTime: () -> Long = { System.currentTimeMillis() },
    onClick: () -> Unit
) = this.composed(
    inspectorInfo = debugInspectorInfo {
        name = "easterEggClickable"
        properties["interactionSource"] = interactionSource
        properties["timeSpan"] = timeSpan
        properties["minimumClicks"] = minimumClicks
        properties["currentTime"] = currentTime
        properties["onClick"] = onClick
    },
    factory = {
        var clickCount by remember {
            mutableIntStateOf(0)
        }

        var firstClickTime by remember {
            mutableLongStateOf(0L)
        }

        val clicked by remember {
            derivedStateOf { clickCount >= minimumClicks }
        }

        return@composed clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = {
                val time = currentTime()

                if (clickCount == 0) {
                    clickCount++
                    firstClickTime = time
                }

                if (time - firstClickTime <= timeSpan) {
                    clickCount++
                } else {
                    clickCount = 0
                }

                if (clicked) {
                    onClick()
                    clickCount = 0
                }
            }
        )
    }
)