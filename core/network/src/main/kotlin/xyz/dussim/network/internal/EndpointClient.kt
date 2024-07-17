package xyz.dussim.network.internal

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get
import xyz.dussim.datamodel.language.dto.LanguageDto
import xyz.dussim.datamodel.skill.dto.SkillDto
import xyz.dussim.network.internal.dto.GymStatsDto

internal class EndpointClient(
    private val httpClient: HttpClient,
) {
    suspend fun fetchSkills(): Result<List<SkillDto>> {
        return runCatching {
            httpClient.get(ApiRoutes.Skills()).body()
        }
    }

    suspend fun fetchLanguages(): Result<List<LanguageDto>> {
        return runCatching {
            httpClient.get(ApiRoutes.Languages()).body()
        }
    }

    suspend fun fetchGymStats(): Result<List<GymStatsDto>> {
        return runCatching {
            httpClient.get(EasterEggsRoutes.GymStats()).body()
        }
    }
}
