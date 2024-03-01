package xyz.dussim.cv

import xyz.dussim.api.components.ActivityComponent
import xyz.dussim.api.components.MapperComponent
import xyz.dussim.datamodel.UniversalMapper
import xyz.dussim.datamodel.language.LanguageMapper
import xyz.dussim.datamodel.skill.SkillMapper

private class MapperComponentImpl(
    activityComponent: ActivityComponent
) : MapperComponent {
    override val languageMapper = LanguageMapper(activityComponent.context)
    override val skillMapper = SkillMapper(activityComponent.context)

    override val universalMapper = UniversalMapper(
        languageMapper = languageMapper,
        skillMapper = skillMapper
    )
}

fun MapperComponent.Companion.create(activityComponent: ActivityComponent): MapperComponent = MapperComponentImpl(activityComponent)
