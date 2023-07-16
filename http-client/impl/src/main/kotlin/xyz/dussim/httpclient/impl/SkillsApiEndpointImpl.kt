package xyz.dussim.httpclient.impl

import xyz.dussim.api.skills.Skill
import xyz.dussim.httpclient.api.SkillsApiEndpoint

internal class SkillsApiEndpointImpl(
    private val endpointClient: EndpointClient
) : SkillsApiEndpoint {
    override suspend fun fetchSkills(): Result<List<Skill>> {
        return endpointClient
            .fetchSkills()
            .map { skillDtos ->
                skillDtos.mapNotNull(SkillDto::mapToSkill)
            }
    }
}