package xyz.dussim.local.impl

import xyz.dussim.api.data.DataSource
import xyz.dussim.datamodel.UniversalMapper
import xyz.dussim.datamodel.skill.Skill
import xyz.dussim.datamodel.skill.dto.SkillDto
import xyz.dussim.datamodel.skill.dto.SkillName
import java.util.Comparator.comparing

internal class LocalSkillsDataSource(
    private val universalMapper: UniversalMapper,
) : DataSource<List<Skill>> by LocalDataSource(staticData(universalMapper)) {
    companion object {
        private fun staticData(universalMapper: UniversalMapper) =
            listOf(
                SkillDto.proficient(SkillName.Kotlin),
                SkillDto.proficient(SkillName.Java),
                SkillDto.proficient(SkillName.Android),
                SkillDto.advanced(SkillName.Dagger2),
                SkillDto.advanced(SkillName.Git),
                SkillDto.competent(SkillName.Ktor),
            )
                .sortedWith(comparing(SkillDto::level).reversed())
                .map(universalMapper::map)
    }
}
