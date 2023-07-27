package xyz.dussim.data.socials


import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

//@Immutable
public data class SocialLink(
    @DrawableRes val icon: Int,
    @StringRes val text: Int,
    val uri: Uri,
    val action: String
)
