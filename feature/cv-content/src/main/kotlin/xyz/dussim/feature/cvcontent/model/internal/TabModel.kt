package xyz.dussim.feature.cvcontent.model.internal

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
internal data class TabModel(
    val tab: Tab,
    @DrawableRes val iconRes: Int?,
    @StringRes val textRes: Int,
) : Parcelable
