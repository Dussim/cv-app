package xyz.dussim.datamodel.skill.dto

import kotlinx.serialization.Serializable

@Serializable
data class SkillDto(
    val name: String,
    val level: SkillLevel,
    val description: String? = null
) {
    companion object {
        operator fun invoke(
            skillLevel: SkillName,
            level: SkillLevel,
            description: String? = null
        ): SkillDto =
            SkillDto(skillLevel.name, level, description)

        fun expert(skillName: SkillName, description: String? = null): SkillDto =
            SkillDto(skillName.name, SkillLevel.Expert, description)

        fun proficient(skillName: SkillName, description: String? = null): SkillDto =
            SkillDto(skillName.name, SkillLevel.Proficient, description)

        fun advanced(skillName: SkillName, description: String? = null): SkillDto =
            SkillDto(skillName.name, SkillLevel.Advanced, description)

        fun competent(skillName: SkillName, description: String? = null): SkillDto =
            SkillDto(skillName.name, SkillLevel.Competent, description)

        fun beginner(skillName: SkillName, description: String? = null): SkillDto =
            SkillDto(skillName.name, SkillLevel.Beginner, description)

        fun expert(name: String, description: String? = null): SkillDto =
            SkillDto(name, SkillLevel.Expert, description)

        fun proficient(name: String, description: String? = null): SkillDto =
            SkillDto(name, SkillLevel.Proficient, description)

        fun advanced(name: String, description: String? = null): SkillDto =
            SkillDto(name, SkillLevel.Advanced, description)

        fun competent(name: String, description: String? = null): SkillDto =
            SkillDto(name, SkillLevel.Competent, description)

        fun beginner(name: String, description: String? = null): SkillDto =
            SkillDto(name, SkillLevel.Beginner, description)
    }
}

@Serializable
enum class SkillLevel(val fraction: Float) {
    Beginner(0.2f),
    Competent(0.4f),
    Advanced(0.6f),
    Proficient(0.8f),
    Expert(1.0f)
}

enum class SkillName {
    Kotlin,
    Java,
    Android,
    Dagger2,
    Git,
    Ktor
}
