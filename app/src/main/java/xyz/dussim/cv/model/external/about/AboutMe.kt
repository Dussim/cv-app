package xyz.dussim.cv.model.external.about

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
data class AboutMe(
    @StringRes val aboutMeRes: Int
)
