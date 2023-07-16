package xyz.dussim.cv.model.repository

import xyz.dussim.api.skills.Skill

fun interface SkillsRepository {
    suspend fun fetchSkills(): List<Skill>
}