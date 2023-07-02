package xyz.dussim.cv.ui.components

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
import xyz.dussim.cv.R
import xyz.dussim.cv.data.ImList
import xyz.dussim.cv.data.LocalTextStyleProvider
import xyz.dussim.cv.model.external.workplace.WorkplaceData
import xyz.dussim.cv.model.external.workplace.periodLabel
import xyz.dussim.cv.ui.components.core.CvDateChip
import xyz.dussim.cv.ui.theme.AccentColor
import xyz.dussim.cv.ui.theme.Body2
import xyz.dussim.cv.ui.theme.Label
import xyz.dussim.cv.ui.theme.TextAlternative

@Composable
fun WorkPeriodHorizontal(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(30.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    workplaces: ImList<WorkplaceData>
) {
    WorkPeriod(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        workplaces.forEach { workplace ->
            WorkPeriodRow(
                dates = {
                    WorkPeriodDates(
                        text = workplace.periodLabel,
                        modifier = Modifier.width(156.dp)//fixme hardcoded length
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
fun WorkPeriodVertical(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(30.dp),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    workplaces: ImList<WorkplaceData>
) {
    WorkPeriod(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        workplaces.forEach { workplace ->
            WorkPeriodColumn(
                dates = {
                    WorkPeriodDates(
                        text = workplace.periodLabel,
                        modifier = Modifier.width(156.dp)//fixme hardcoded length
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
fun WorkPeriod(
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
fun WorkPeriodColumn(
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
fun WorkPeriodRow(
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
fun WorkPeriodDates(
    text: String,
    modifier: Modifier = Modifier
) {
    CvDateChip(text, modifier)
}

@Composable
fun WorkPeriodTitle(
    text: String
) {
    val style = LocalTextStyleProvider.current.forWorkTitle()
    BasicText(text = text, style = style)
}

@Composable
fun WorkPeriodPlace(
    text: String
) {
    BasicText(text = text.uppercase(), style = Label.copy(color = AccentColor))
}

@Composable
fun WorkPeriodDescription(
    text: String
) {
    BasicText(text = text, style = Body2.copy(color = TextAlternative))
}


@Composable
private fun WorkPeriodColumnPreview() {
    val desc = """
        Worked on new features and bug fixes for Noggin application for kids. The work involved working with a modern big multi-module MVVM project (200+modules) with 80+% unit tests coverage.
    """.trimIndent()

    WorkPeriodColumn(
        dates = { WorkPeriodDates(text = "2020-09 – 2022-04") },
        title = { WorkPeriodTitle(text = "Android Developer") },
        place = { WorkPeriodPlace(text = "Paramount, Warszawa, Polska") },
        description = { WorkPeriodDescription(text = desc) }
    )
}


@Composable
private fun WorkPeriodRowPreview() {
    val desc = """
        Worked on new features and bug fixes for Noggin application for kids. The work involved working with a modern big multi-module MVVM project (200+modules) with 80+% unit tests coverage.
    """.trimIndent()

    WorkPeriodRow(
        dates = { WorkPeriodDates(text = "2020-09 – 2022-04") },
        title = { WorkPeriodTitle(text = "Android Developer") },
        place = { WorkPeriodPlace(text = "Paramount, Warszawa, Polska") },
        description = { WorkPeriodDescription(text = desc) }
    )
}