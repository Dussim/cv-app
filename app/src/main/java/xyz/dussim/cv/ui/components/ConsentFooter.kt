package xyz.dussim.cv.ui.components

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import xyz.dussim.resources.R

@Composable
fun ConsentFooter(
    modifier: Modifier = Modifier,
    text: String = stringResource(R.string.footer_content)
) {
    BasicText(
        text = text,
        style = xyz.dussim.designsystem.Label.copy(
            fontStyle = FontStyle.Italic,
            color = xyz.dussim.designsystem.TextAlternative
        ),
        modifier = modifier
    )
}
