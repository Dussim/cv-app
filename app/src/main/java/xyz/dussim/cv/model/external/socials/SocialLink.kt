package xyz.dussim.cv.model.external.socials

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
data class SocialLink(
    @DrawableRes val icon: Int,
    @StringRes val text: Int,
    val uri: Uri,
    val action: String
)
