package xyz.dussim.data.about

import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class AboutMe(
    @StringRes val aboutMeRes: Int,
) : Parcelable
