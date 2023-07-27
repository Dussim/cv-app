package xyz.dussim.network.internal.dto

import kotlinx.serialization.Serializable
import xyz.dussim.data.skills.Skill
import xyz.dussim.network.internal.util.*

@Serializable
internal data class SkillDto(
    val name: String?,
    val level: String?
)

internal fun SkillDto.mapToSkill(): Skill? {
    return Maybe.of(::Skill)
        .compose2 { maybeOf(name) }
        .compose { maybeOf(level) }
        .orNull()
}