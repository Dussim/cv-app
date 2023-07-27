package xyz.dussim.designsystem.core


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.math.max
import kotlin.math.min

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HidingHeader(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    contentScrollableState: ScrollState = rememberScrollState(),
    header: @Composable () -> Unit,
    content: @Composable (ScrollState) -> Unit
) {
    val layoutDirection = LocalLayoutDirection.current

    var offsetMax by remember {
        mutableFloatStateOf(Float.MAX_VALUE)
    }

    var offset by remember {
        mutableFloatStateOf(0f)
    }

    val childScroll by remember {
        derivedStateOf { contentScrollableState.value }
    }

    val scrollableState = rememberScrollableState { delta ->
        if (delta == 0f) {
            return@rememberScrollableState delta
        }
        if (childScroll > 0 && delta > 0) {
            contentScrollableState.dispatchRawDelta(-delta)
            return@rememberScrollableState delta
        } else {
            val resultingOffset = offset - delta
            offset = resultingOffset
            if (delta < 0) {
                //scrolling down
                offset = min(offset, offsetMax)
                val leftover = resultingOffset - offset
                return@rememberScrollableState (delta + leftover)
            } else {
                //scrolling up, always consume all
                offset = max(offset, 0f)
                return@rememberScrollableState delta
            }
        }
    }

    val nestedScrollConnection = remember {
        CollapsibleTopBarNestedScroll(scrollableState)
    }


    Layout(
        modifier = modifier
            .scrollable(
                state = scrollableState,
                orientation = Orientation.Vertical,
                overscrollEffect = ScrollableDefaults.overscrollEffect()
            )
            .nestedScroll(nestedScrollConnection),
        content = {
            header()
            content(contentScrollableState)
        }
    ) { measurables, constraints ->
        val top = contentPadding.calculateTopPadding().toPx()
        val bottom = contentPadding.calculateBottomPadding().toPx()
        val start = contentPadding.calculateStartPadding(layoutDirection).toPx()
        val end = contentPadding.calculateEndPadding(layoutDirection).toPx()

        val placeables = measurables.mapIndexed { index, measurable ->
            measurable.measure(
                if (index == 0) {
                    constraints.copy(
                        maxHeight = Int.MAX_VALUE,
                        maxWidth = (constraints.maxWidth - start - end).toInt()
                    )
                } else {
                    constraints.copy(
                        maxHeight = (constraints.maxHeight - bottom).toInt(),
                        maxWidth = (constraints.maxWidth - start - end).toInt()
                    )
                }
            )
        }

        offsetMax = (placeables.firstOrNull()?.height?.toFloat() ?: 0f) + top

        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEachIndexed { i, placeable ->
                val currentOffset = placeables.take(i).sumOf { it.measuredHeight } - offset.toInt() + top.toInt()
                placeable.placeRelative(start.toInt(), currentOffset)
            }
        }
    }
}

private class CollapsibleTopBarNestedScroll(
    private val scrollableState: ScrollableState
) : NestedScrollConnection {

    override fun onPreScroll(
        available: Offset,
        source: NestedScrollSource
    ): Offset {
        if (available.y == 0f) {
            //if there is no vertical scroll to consume we don't do anything
            return Offset.Zero
        }

        return available.copy(y = scrollableState.dispatchRawDelta(available.y))
    }
}