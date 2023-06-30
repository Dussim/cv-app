package xyz.dussim.cv.model.external.workplace

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import xyz.dussim.cv.R
import xyz.dussim.cv.data.CvDatePattern
import java.time.YearMonth

@Immutable
data class WorkplaceData(
    val startDate: YearMonth,
    val endDate: YearMonth?,
    @StringRes val workTitle: Int,
    @StringRes val location: Int,
    @StringRes val description: Int
)

inline val WorkplaceData.startLabel: String
    @Composable get() = remember {
        CvDatePattern.format(startDate)
    }

inline val WorkplaceData.endLabel: String
    @Composable get() = when (endDate) {
        null -> stringResource(id = R.string.workplace_dates_present)
        else -> remember { CvDatePattern.format(endDate) }
    }

inline val WorkplaceData.periodLabel: String
    @Composable get() {
        val startLabel = startLabel
        val endLabel = endLabel

        return remember { "$startLabel – $endLabel" }
    }