package xyz.dussim.data.workplace

import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import xyz.dussim.data.YearMonth

@Immutable
@Parcelize
data class Workplace(
    val startDate: YearMonth,
    val endDate: YearMonth?,
    @StringRes val workTitle: Int,
    @StringRes val location: Int,
    @StringRes val description: Int,
) : Parcelable
