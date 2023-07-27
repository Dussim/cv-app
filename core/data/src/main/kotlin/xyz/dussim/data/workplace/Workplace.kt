package xyz.dussim.data.workplace

import androidx.annotation.StringRes
import java.time.YearMonth

//@Immutable
public data class Workplace(
    val startDate: YearMonth,
    val endDate: YearMonth?,
    @StringRes val workTitle: Int,
    @StringRes val location: Int,
    @StringRes val description: Int
)