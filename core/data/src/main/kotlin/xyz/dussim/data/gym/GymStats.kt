package xyz.dussim.data.gym

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
sealed interface Weight : Parcelable {
    companion object {
        fun default(value: Double): Weight = Kilogram(value)
    }

    val value: Double

    @Immutable
    @Parcelize
    data class Kilogram(
        override val value: Double,
    ) : Weight

    @Immutable
    @Parcelize
    data class Pounds(
        override val value: Double,
    ) : Weight
}

@Immutable
@Parcelize
data class GymStats(
    val name: String,
    val reps: Int?,
    val weight: Weight?,
) : Parcelable
