package xyz.dussim.cv.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import xyz.dussim.cv.R
import xyz.dussim.cv.data.ImList
import xyz.dussim.cv.data.LocalTextStyleProvider
import xyz.dussim.cv.ui.components.core.CvLinearProgressBar

@Composable
fun SkillsColumn(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(30.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    skills: ImList<xyz.dussim.api.skills.Skill>
) {
    val style = LocalTextStyleProvider.current.forSectionTitle()
    val skillStyle = LocalTextStyleProvider.current.forSkills()
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        BasicText(text = stringResource(R.string.section_name_skills), style = style)
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            skills.forEach {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BasicText(
                        text = it.name.toString(),
                        style = skillStyle,
                        modifier = Modifier.weight(1f)
                    )
                    CvLinearProgressBar(
                        cornerRadius = 40.dp,
                        fraction = it.level.fraction,
                        modifier = Modifier
                            .weight(1f)
                            .height(10.dp)
                    )
                }
            }
        }
    }
}