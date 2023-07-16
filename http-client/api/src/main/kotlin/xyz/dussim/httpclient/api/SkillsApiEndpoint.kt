package xyz.dussim.httpclient.api

import xyz.dussim.api.skills.Skill

public fun interface SkillsApiEndpoint {
    public suspend fun fetchSkills(): Result<List<Skill>>
}