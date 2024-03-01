package xyz.dussim.feature.cvcontent.components

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
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import xyz.dussim.datamodel.skill.Skill
import xyz.dussim.designsystem.LocalTextStyleProvider
import xyz.dussim.designsystem.core.CvLinearProgressBar
import xyz.dussim.designsystem.core.modifiers.easterEggClickable
import xyz.dussim.feature.cvcontent.R
import xyz.dussim.navigation.CvAppScreens

@Composable
internal fun SkillsColumn(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(30.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    skills: List<Skill>
) {
    val navigator = LocalNavigator.currentOrThrow
    val gymStatsEasterEgg = rememberScreen(CvAppScreens.GymStats)

    val style = LocalTextStyleProvider.current.forSectionTitle()
    val skillStyle = LocalTextStyleProvider.current.forSkills()
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        BasicText(
            text = stringResource(R.string.section_name_skills),
            style = style,
            modifier = Modifier.easterEggClickable {
                navigator.push(gymStatsEasterEgg)
            }
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            skills.forEach {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BasicText(
                        text = it.name,
                        style = skillStyle,
                        modifier = Modifier.weight(1f)
                    )
                    CvLinearProgressBar(
                        cornerRadius = 40.dp,
                        fraction = it.fraction,
                        modifier = Modifier
                            .weight(1f)
                            .height(10.dp)
                    )
                }
            }
        }
    }
}
