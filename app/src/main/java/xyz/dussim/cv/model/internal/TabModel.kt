package xyz.dussim.cv.model.internal

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class TabModel(
    val tab: Tab,
    @DrawableRes val iconRes: Int?,
    @StringRes val textRes: Int
)
