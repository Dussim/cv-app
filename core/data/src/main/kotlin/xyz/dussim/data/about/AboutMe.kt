package xyz.dussim.data.about

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

//import androidx.compose.runtime.Immutable

//@Immutable
@Parcelize
public data class AboutMe(
    @StringRes val aboutMeRes: Int
) : Parcelable
