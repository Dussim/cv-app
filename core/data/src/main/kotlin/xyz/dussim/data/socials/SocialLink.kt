package xyz.dussim.data.socials

import android.net.Uri
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class SocialLink(
    @DrawableRes val icon: Int,
    @StringRes val text: Int,
    val uri: Uri,
    val action: String
) : Parcelable
