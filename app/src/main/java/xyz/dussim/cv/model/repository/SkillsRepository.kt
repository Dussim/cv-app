package xyz.dussim.cv.model.repository

import xyz.dussim.cv.model.external.skills.Skill

fun interface SkillsRepository {
    suspend fun fetchSkills(): List<Skill>
}