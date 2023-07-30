@file:OptIn(ExperimentalLayoutApi::class)

package xyz.dussim.feature.cvcontent.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import xyz.dussim.data.languages.Language
import xyz.dussim.designsystem.LocalTextStyleProvider
import xyz.dussim.resources.R
import xyz.dussim.ui.language.LanguageChip


@Composable
internal fun LanguagesColumn(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(30.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    languages: List<Language>
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