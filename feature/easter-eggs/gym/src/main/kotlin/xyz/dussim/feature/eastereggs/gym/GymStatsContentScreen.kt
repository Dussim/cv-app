package xyz.dussim.feature.eastereggs.gym

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.parcelize.Parcelize
import xyz.dussim.data.gym.GymStats
import xyz.dussim.designsystem.LocalScreenWidthClass
import xyz.dussim.designsystem.LocalTextStyleProvider
import xyz.dussim.designsystem.ScreenWidthClass
import xyz.dussim.designsystem.core.CvChip
import xyz.dussim.designsystem.margin_1x
import xyz.dussim.designsystem.margin_2_5x
import xyz.dussim.feature.ee.gym.R
import xyz.dussim.navigation.ParcelableScreen

@Parcelize
internal data class GymStatsContentScreen(val gymStats: List<GymStats>) : ParcelableScreen {
    @Composable
    override fun Content() {
        val screenWidthClass = LocalScreenWidthClass.current

        val padding =
            when (screenWidthClass) {
                ScreenWidthClass.Small -> PaddingValues(margin_1x)
                ScreenWidthClass.Medium -> PaddingValues(margin_1x)
                ScreenWidthClass.Big -> PaddingValues(margin_2_5x)
            }

        GymStatsColumn(
            gymStats = gymStats,
            modifier =
                Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState()),
        )
    }
}

@Composable
internal fun GymStatsColumn(
    gymStats: List<GymStats>,
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(30.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
) {
    val style = LocalTextStyleProvider.current.forSectionTitle()
    val skillStyle = LocalTextStyleProvider.current.forSkills()

    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
    ) {
        BasicText(
            text = stringResource(R.string.section_name_gym_stats),
            style = style,
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            gymStats.forEach {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    BasicText(
                        text = it.name,
                        style = skillStyle,
                        modifier = Modifier.weight(1f),
                    )
                    Row(
                        modifier = Modifier.weight(2f),
                        horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.End),
                    ) {
                        it.weight?.value?.let { weight ->
                            CvChip {
                                BasicText(
                                    text = stringResource(id = R.string.kilograms, weight.toInt()),
                                    style = skillStyle,
                                )
                            }
                        }
                        it.reps?.let { reps ->
                            CvChip {
                                BasicText(
                                    pluralStringResource(id = R.plurals.reps_count, count = reps, reps),
                                    style = skillStyle,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
