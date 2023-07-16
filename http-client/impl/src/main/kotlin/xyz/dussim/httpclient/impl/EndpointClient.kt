package xyz.dussim.httpclient.impl

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class EndpointClient(
    private val httpClient: HttpClient
) {
    suspend fun fetchSkills(): Result<List<SkillDto>> {
        return runCatching {
            httpClient.get("api/skills").body()
        }
    }
}