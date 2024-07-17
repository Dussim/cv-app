package xyz.dussim.data.about

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class AboutMe(
    @StringRes val aboutMeRes: Int,
) : Parcelable
