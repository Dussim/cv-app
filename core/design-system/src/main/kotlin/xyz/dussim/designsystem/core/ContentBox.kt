package xyz.dussim.designsystem.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import xyz.dussim.designsystem.BackgroundBlurBrush
import xyz.dussim.designsystem.LocalScreenWidthClass
import xyz.dussim.designsystem.LocalTextStyleProvider
import xyz.dussim.designsystem.ScreenWidthClass
import xyz.dussim.designsystem.TextStyleProvider

private fun ScreenWidthClass.toTextStyleProvider() =
    when (this) {
        ScreenWidthClass.Small -> TextStyleProvider.Default
        else -> TextStyleProvider.Big
    }

@Composable
fun ContentBox(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    val screenWidthClass = LocalScreenWidthClass.current

    val textStyleProvider = remember { screenWidthClass.toTextStyleProvider() }

    Box(
        modifier =
            Modifier
                .background(BackgroundBlurBrush)
                .safeDrawingPadding()
                .clipToBounds()
                .then(modifier),
    ) {
        CompositionLocalProvider(
            LocalTextStyleProvider.provides(textStyleProvider),
        ) {
            content()
        }
    }
}
