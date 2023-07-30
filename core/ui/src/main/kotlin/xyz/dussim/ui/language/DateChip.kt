package xyz.dussim.ui.language

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import xyz.dussim.designsystem.Caption
import xyz.dussim.designsystem.TextAlternative
import xyz.dussim.designsystem.core.CvChip
import xyz.dussim.resources.R
import java.time.YearMonth

@Composable
fun DateChip(
    modifier: Modifier = Modifier,
    date: YearMonth
) {
    CvChip(modifier = modifier) {
        BasicText(
            text = CvDateFormatter.format(date),
            style = Caption.copy(TextAlternative),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun TimeFrameChip(
    modifier: Modifier = Modifier,
    start: YearMonth?,
    end: YearMonth?
) {
    val text = if (start != null && end != null) {
        "${CvDateFormatter.format(start)} - ${CvDateFormatter.format(end)}"
    } else if (start != null) {
        val endText = stringResource(id = R.string.workplace_dates_present)
        "${CvDateFormatter.format(start)} - $endText"
    } else if (end != null) {
        CvDateFormatter.format(end)
    } else {
        throw IllegalArgumentException("At least one of start or end must be non-null")
    }

    CvChip(modifier = modifier) {
        BasicText(
            text = text,
            style = Caption.copy(TextAlternative),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}