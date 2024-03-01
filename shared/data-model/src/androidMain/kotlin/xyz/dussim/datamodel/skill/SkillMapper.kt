package xyz.dussim.datamodel.skill

import android.content.Context
import xyz.dussim.datamodel.skill.dto.SkillDto
import xyz.dussim.datamodel.skill.dto.toDisplayString

fun interface SkillMapper {
    companion object {
        operator fun invoke(context: Context) = SkillMapper { skillDto ->
            Skill(
                name = skillDto.name,
                fraction = skillDto.level.fraction,
                contentDescription = skillDto.level.toDisplayString(context),
            )
        }
    }

    fun map(skillDto: SkillDto): Skill

    fun mapSkills(skillDtos: List<SkillDto>): List<Skill> = skillDtos.map(::map)
}
