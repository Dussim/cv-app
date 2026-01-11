package xyz.dussim.data.socials

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class SocialLink(
    @DrawableRes val icon: Int,
    @StringRes val text: Int,
    val uriString: String,
    val action: String,
) : Parcelable
