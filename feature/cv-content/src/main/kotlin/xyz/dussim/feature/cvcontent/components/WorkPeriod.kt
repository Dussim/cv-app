package xyz.dussim.feature.cvcontent.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import xyz.dussim.data.workplace.Workplace
import xyz.dussim.designsystem.*
import xyz.dussim.feature.cvcontent.R
import xyz.dussim.ui.language.TimeFrameChip

@Composable
internal fun WorkPeriodHorizontal(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(30.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    workplaces: List<Workplace>
) {
    WorkPeriod(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        workplaces.forEach { workplace ->
            WorkPeriodRow(
                dates = {
                    TimeFrameChip(
                        modifier = Modifier.width(156.dp),//fixme hardcoded length
                        start = workplace.startDate,
                        end = workplace.endDate
                    )
                },
                title = { WorkPeriodTitle(text = stringResource(id = workplace.workTitle)) },
                place = { WorkPeriodPlace(text = stringResource(id = workplace.location)) },
                description = { WorkPeriodDescription(text = stringResource(id = workplace.description)) }
            )
        }
    }
}

@Composable
internal fun WorkPeriodVertical(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(30.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    workplaces: List<Workplace>
) {
    WorkPeriod(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        workplaces.forEach { workplace ->
            WorkPeriodColumn(
                dates = {
                    TimeFrameChip(
                        modifier = Modifier.width(156.dp),//fixme hardcoded length
                        start = workplace.startDate,
                        end = workplace.endDate
                    )
                },
                title = { WorkPeriodTitle(text = stringResource(id = workplace.workTitle)) },
                place = { WorkPeriodPlace(text = stringResource(id = workplace.location)) },
                description = { WorkPeriodDescription(text = stringResource(id = workplace.description)) }
            )
        }
    }
}

@Composable
internal fun WorkPeriod(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(30.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable () -> Unit
) {
    val style = LocalTextStyleProvider.current.forSectionTitle()
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        BasicText(text = stringResource(R.string.section_name_work_history), style = style)
        content()
    }
}


@Composable
internal fun WorkPeriodColumn(
    title: @Composable () -> Unit,
    place: @Composable () -> Unit,
    description: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(8.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    dates: (@Composable () -> Unit)? = null,
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        dates?.invoke()
        title()
        place()
        description()
    }
}

@Composable
internal fun WorkPeriodRow(
    dates: @Composable () -> Unit,
    title: @Composable () -> Unit,
    place: @Composable () -> Unit,
    description: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(20.dp),
    verticalAlignment: Alignment.Vertical = Alignment.Top,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment
    ) {
        dates()
        WorkPeriodColumn(
            title = title,
            place = place,
            description = description
        )
    }
}

@Composable
internal fun WorkPeriodTitle(
    text: String
) {
    val style = LocalTextStyleProvider.current.forWorkTitle()
    BasicText(text = text, style = style)
}

@Composable
internal fun WorkPeriodPlace(
    text: String
) {
    BasicText(text = text.uppercase(), style = Label.copy(color = AccentColor))
}

@Composable
internal fun WorkPeriodDescription(
    text: String
) {
    BasicText(text = text, style = Body2.copy(color = TextAlternative))
}