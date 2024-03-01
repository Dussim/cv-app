package xyz.dussim.datamodel.language

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Language(
    val name: String,
    val level: String
) : Parcelable
