package xyz.dussim.cv.ui.components

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import xyz.dussim.cv.R
import xyz.dussim.cv.ui.theme.Label
import xyz.dussim.cv.ui.theme.TextAlternative


@Composable
fun ConsentFooter(
    modifier: Modifier = Modifier,
    text: String = stringResource(R.string.footer_content)
) {
    BasicText(
        text = text,
        style = Label.copy(
            fontStyle = FontStyle.Italic,
            color = TextAlternative
        ),
        modifier = modifier
    )
}