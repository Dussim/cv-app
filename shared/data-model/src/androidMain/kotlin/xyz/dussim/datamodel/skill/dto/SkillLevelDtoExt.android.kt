package xyz.dussim.datamodel.skill.dto

import android.content.Context
import xyz.dussim.datamodel.R
import xyz.dussim.datamodel.skill.dto.SkillLevel.Advanced
import xyz.dussim.datamodel.skill.dto.SkillLevel.Beginner
import xyz.dussim.datamodel.skill.dto.SkillLevel.Competent
import xyz.dussim.datamodel.skill.dto.SkillLevel.Expert
import xyz.dussim.datamodel.skill.dto.SkillLevel.Proficient

fun SkillLevel.toDisplayString(context: Context): String = when (this) {
    Beginner -> context.getString(R.string.data_model_skill_level_beginner)
    Competent -> context.getString(R.string.data_model_skill_level_competent)
    Advanced -> context.getString(R.string.data_model_skill_level_advanced)
    Proficient -> context.getString(R.string.data_model_skill_level_proficient)
    Expert -> context.getString(R.string.data_model_skill_level_expert)
}
