package xyz.dussim.ui.language

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import xyz.dussim.datamodel.language.Language
import xyz.dussim.designsystem.LocalTextStyleProvider
import xyz.dussim.designsystem.core.CvChip
import xyz.dussim.designsystem.core.CvIcon
import xyz.dussim.designsystem.margin_1x
import xyz.dussim.ui.R

@Composable
fun LanguageChip(
    language: Language,
    modifier: Modifier = Modifier,
) {
    val style = LocalTextStyleProvider.current.forLanguageChip()

    CvChip(modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(margin_1x),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CvIcon(R.drawable.languages)
            BasicText(text = "${language.name}:${language.level}", style = style)
        }
    }
}
