package xyz.dussim.network.internal

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.resources.*
import xyz.dussim.network.internal.dto.SkillDto

internal class EndpointClient(
    private val httpClient: HttpClient
) {
    suspend fun fetchSkills(): Result<List<SkillDto>> {
        return runCatching {
            return httpClient.get(ApiRoutes.Skills()).body()
        }
    }
}