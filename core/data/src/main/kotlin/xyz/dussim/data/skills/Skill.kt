package xyz.dussim.data.skills

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Skill(
    val name: SkillName,
    val level: SkillLevel
) : Parcelable {

    companion object {
        fun expert(skillName: SkillName): Skill {
            return Skill(skillName, SkillLevel.Expert)
        }

        fun proficient(skillName: SkillName): Skill {
            return Skill(skillName, SkillLevel.Proficient)
        }

        fun advanced(skillName: SkillName): Skill {
            return Skill(skillName, SkillLevel.Advanced)
        }

        fun competent(skillName: SkillName): Skill {
            return Skill(skillName, SkillLevel.Competent)
        }

        fun beginner(skillName: SkillName): Skill {
            return Skill(skillName, SkillLevel.Beginner)
        }
    }
}