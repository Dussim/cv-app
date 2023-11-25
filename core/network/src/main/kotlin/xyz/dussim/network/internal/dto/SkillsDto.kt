package xyz.dussim.network.internal.dto

import kotlinx.serialization.Serializable
import xyz.dussim.data.skills.Skill
import xyz.dussim.data.skills.SkillLevel
import xyz.dussim.data.skills.SkillName

@Serializable
internal data class SkillDto(
    val name: String?,
    val level: String?
)

internal fun SkillDto.mapToSkill(): Skill? {
    val name = name ?: return null
    val level = level ?: return null

    return runCatching {
        return Skill(
            SkillName.valueOf(name),
            SkillLevel.valueOf(level)
        )
    }.getOrNull()
}