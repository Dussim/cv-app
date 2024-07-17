package xyz.dussim.feature.cvcontent.model.internal

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

internal data class TabModel(
    val tab: Tab,
    @DrawableRes val iconRes: Int?,
    @StringRes val textRes: Int,
)
