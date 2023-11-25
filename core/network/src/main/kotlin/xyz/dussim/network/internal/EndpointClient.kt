package xyz.dussim.network.internal

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.resources.*
import xyz.dussim.network.internal.dto.GymStatsDto
import xyz.dussim.network.internal.dto.SkillDto

internal class EndpointClient(
    private val httpClient: HttpClient
) {
    suspend fun fetchSkills(): Result<List<SkillDto>> {
        return runCatching {
            httpClient.get(ApiRoutes.Skills()).body()
        }
    }

    suspend fun fetchGymStats(): Result<List<GymStatsDto>> {
        return runCatching {
            httpClient.get(EasterEggsRoutes.GymStats()).body()
        }
    }
}