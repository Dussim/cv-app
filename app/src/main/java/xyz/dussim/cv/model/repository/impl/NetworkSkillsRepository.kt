package xyz.dussim.cv.model.repository.impl

import xyz.dussim.api.skills.Skill
import xyz.dussim.cv.model.repository.SkillsRepository
import xyz.dussim.httpclient.api.SkillsApiEndpoint

internal class NetworkSkillsRepository(
    private val skillsApiEndpoint: SkillsApiEndpoint,
    private val fallback: SkillsRepository
) : SkillsRepository {
    override suspend fun fetchSkills(): List<Skill> {
        return skillsApiEndpoint.fetchSkills().getOrElse { fallback.fetchSkills() }
    }
}