package xyz.dussim.data.certificates

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import java.time.YearMonth

@Parcelize
data class Certificate(
    @StringRes val name: Int,
    val date: YearMonth,
    val link: String? = null,
) : Parcelable
