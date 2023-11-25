package xyz.dussim.network.internal.dto

import kotlinx.serialization.Serializable
import xyz.dussim.data.gym.GymStats
import xyz.dussim.data.gym.Weight

@Serializable
internal data class GymStatsDto(
    val name: String?,
    val reps: Int?,
    val weight: Double?
)

internal fun GymStatsDto.mapToGymStats(): GymStats? {
    val name = name ?: return null
    val reps = reps
    val weight = weight?.let(Weight::default)

    if (reps == null && weight == null) return null

    return GymStats(name, reps, weight)
}