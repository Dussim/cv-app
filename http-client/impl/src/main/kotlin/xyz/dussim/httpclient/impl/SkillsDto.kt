package xyz.dussim.httpclient.impl

import kotlinx.serialization.Serializable
import xyz.dussim.api.skills.Skill
import xyz.dussim.httpclient.impl.util.Maybe
import xyz.dussim.httpclient.impl.util.compose
import xyz.dussim.httpclient.impl.util.compose2
import xyz.dussim.httpclient.impl.util.maybeOf
import xyz.dussim.httpclient.impl.util.orNull

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