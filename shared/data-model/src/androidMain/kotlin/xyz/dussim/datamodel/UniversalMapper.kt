package xyz.dussim.datamodel

import xyz.dussim.datamodel.language.LanguageMapper
import xyz.dussim.datamodel.skill.SkillMapper

class UniversalMapper(
    private val languageMapper: LanguageMapper,
    private val skillMapper: SkillMapper
) : LanguageMapper by languageMapper,
    SkillMapper by skillMapper
