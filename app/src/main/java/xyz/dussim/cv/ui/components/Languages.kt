@file:OptIn(ExperimentalLayoutApi::class)

package xyz.dussim.cv.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import xyz.dussim.cv.R
import xyz.dussim.cv.data.ImList
import xyz.dussim.cv.data.LocalTextStyleProvider
import xyz.dussim.cv.model.external.languages.Language
import xyz.dussim.cv.ui.components.core.CvIcon
import xyz.dussim.cv.ui.theme.DisabledColor

@Composable
fun Language.Level.string(): String = when (this) {
    is Language.NonTranslatable -> name
    is Language.Translatable -> stringResource(resId)
}

@Composable
fun LanguagesColumn(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(30.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    languages: ImList<Language>
) {
    val style = LocalTextStyleProvider.current.forSectionTitle()
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        BasicText(text = stringResource(R.string.section_name_languages), style = style)
        val arrangement = Arrangement.spacedBy(16.dp)
        FlowRow(
            verticalArrangement = arrangement,
            horizontalArrangement = arrangement
        ) {
            languages.forEach {
                LanguageChip(language = it)
            }
        }
    }
}

@Composable
fun LanguageChip(
    language: Language,
    modifier: Modifier = Modifier
) {
    val languageName = stringResource(id = language.languageRes)
    val languageLevel = language.level.string()
    val style = LocalTextStyleProvider.current.forLanguageChip()
    Box(
        modifier =
        Modifier
            .background(DisabledColor, RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .then(modifier)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CvIcon(R.drawable.languages)
            BasicText(text = "$languageName:$languageLevel", style = style)
        }
    }
}