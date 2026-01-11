package xyz.dussim.data.certificates

import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import xyz.dussim.data.YearMonth

@Immutable
@Parcelize
data class Certificate(
    @StringRes val name: Int,
    val date: YearMonth,
    val link: String? = null,
) : Parcelable
