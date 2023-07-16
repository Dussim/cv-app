package xyz.dussim.cv.model.repository.impl

import xyz.dussim.api.skills.Skill
import xyz.dussim.api.skills.SkillName
import xyz.dussim.cv.model.repository.SkillsRepository
import java.util.Comparator.comparing

object StaticSkillsRepository : SkillsRepository {
    private val STATIC_DATA = listOf(
        Skill.proficient(SkillName.Kotlin),
        Skill.proficient(SkillName.Java),
        Skill.proficient(SkillName.Android),
        Skill.advanced(SkillName.Dagger2),
        Skill.advanced(SkillName.Git),
//        Skill.beginner(SkillName.Spring)
    ).sortedWith(comparing(Skill::level).reversed())

    override suspend fun fetchSkills() = STATIC_DATA
}