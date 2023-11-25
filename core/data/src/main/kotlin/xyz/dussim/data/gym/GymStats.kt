package xyz.dussim.data.gym

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface Weight : Parcelable {
    companion object {
        fun default(value: Double): Weight = Kilogram(value)
    }

    val value: Double

    @Parcelize
    data class Kilogram(override val value: Double) : Weight

    @Parcelize
    data class Pounds(override val value: Double) : Weight
}

@Parcelize
data class GymStats(
    val name: String,
    val reps: Int?,
    val weight: Weight?
) : Parcelable
