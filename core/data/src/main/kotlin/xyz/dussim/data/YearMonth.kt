package xyz.dussim.data

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import java.time.Month
import java.time.temporal.TemporalAccessor
import java.time.temporal.TemporalField
import java.time.temporal.TemporalQuery
import java.time.temporal.ValueRange

@Immutable
@Parcelize
class YearMonth(
    val yearMonth: java.time.YearMonth,
) : TemporalAccessor by yearMonth,
    Parcelable {
    companion object {
        fun of(
            year: Int,
            month: Month,
        ): YearMonth = YearMonth(java.time.YearMonth.of(year, month))
    }

    override fun get(field: TemporalField?): Int = yearMonth.get(field)

    override fun <R> query(query: TemporalQuery<R?>?): R? = yearMonth.query(query)

    override fun range(field: TemporalField?): ValueRange? = yearMonth.range(field)
}
