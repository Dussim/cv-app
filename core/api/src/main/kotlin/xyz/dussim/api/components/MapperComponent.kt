package xyz.dussim.api.components

import xyz.dussim.datamodel.UniversalMapper
import xyz.dussim.datamodel.language.LanguageMapper
import xyz.dussim.datamodel.skill.SkillMapper

interface MapperComponent {
    companion object;

    val universalMapper: UniversalMapper
    val languageMapper: LanguageMapper
    val skillMapper: SkillMapper
}
