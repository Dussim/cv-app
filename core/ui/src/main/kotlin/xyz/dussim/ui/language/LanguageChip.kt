package xyz.dussim.ui.language

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import xyz.dussim.data.languages.Language
import xyz.dussim.designsystem.LocalTextStyleProvider
import xyz.dussim.designsystem.core.CvChip
import xyz.dussim.designsystem.core.CvIcon
import xyz.dussim.designsystem.margin_1x
import xyz.dussim.resources.R

@Composable
private fun Language.Level.string(): String = when (this) {
    is Language.NonTranslatable -> name
    is Language.Translatable -> stringResource(resId)
}

@Composable
fun LanguageChip(
    language: Language,
    modifier: Modifier = Modifier
) {
    val languageName = stringResource(id = language.languageRes)
    val languageLevel = language.level.string()
    val style = LocalTextStyleProvider.current.forLanguageChip()

    CvChip(modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(margin_1x),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CvIcon(R.drawable.languages)
            BasicText(text = "$languageName:$languageLevel", style = style)
        }
    }
}