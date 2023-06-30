package xyz.dussim.cv.model.external.skills

import androidx.compose.runtime.Immutable

@Immutable
data class Skill(
    val name: SkillName,
    val level: SkillLevel
) {
    companion object {
        fun expert(skillName: SkillName) = Skill(skillName, SkillLevel.Expert)
        fun proficient(skillName: SkillName) = Skill(skillName, SkillLevel.Proficient)
        fun advanced(skillName: SkillName) = Skill(skillName, SkillLevel.Advanced)
        fun competent(skillName: SkillName) = Skill(skillName, SkillLevel.Competent)
        fun beginner(skillName: SkillName) = Skill(skillName, SkillLevel.Beginner)
    }
}