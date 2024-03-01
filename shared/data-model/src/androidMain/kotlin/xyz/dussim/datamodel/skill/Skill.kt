package xyz.dussim.datamodel.skill

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Skill(
    val name: String,
    val fraction: Float,
    val contentDescription: String
) : Parcelable
