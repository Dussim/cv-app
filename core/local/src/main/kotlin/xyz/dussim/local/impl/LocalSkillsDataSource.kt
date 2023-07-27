package xyz.dussim.local.impl

import xyz.dussim.api.data.DataSource
import xyz.dussim.data.skills.Skill
import xyz.dussim.data.skills.SkillName
import java.util.Comparator.comparing

internal class LocalSkillsDataSource : DataSource<List<Skill>> by LocalDataSource(STATIC_DATA) {
    companion object {
        private val STATIC_DATA = listOf(
            Skill.proficient(SkillName.Kotlin),
            Skill.proficient(SkillName.Java),
            Skill.proficient(SkillName.Android),
            Skill.advanced(SkillName.Dagger2),
            Skill.advanced(SkillName.Git),
            Skill.beginner(SkillName.Spring)
        ).sortedWith(comparing(Skill::level).reversed())
    }
}