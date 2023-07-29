package xyz.dussim.data.workplace

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import java.time.YearMonth

//@Immutable
@Parcelize
public data class Workplace(
    val startDate: YearMonth,
    val endDate: YearMonth?,
    @StringRes val workTitle: Int,
    @StringRes val location: Int,
    @StringRes val description: Int
) : Parcelable