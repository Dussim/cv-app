package xyz.dussim.data.skills

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
public data class Skill(
    val name: SkillName,
    val level: SkillLevel
) : Parcelable {

    public companion object {
        public fun expert(skillName: SkillName): Skill {
            return Skill(skillName, SkillLevel.Expert)
        }

        public fun proficient(skillName: SkillName): Skill {
            return Skill(skillName, SkillLevel.Proficient)
        }

        public fun advanced(skillName: SkillName): Skill {
            return Skill(skillName, SkillLevel.Advanced)
        }

        public fun competent(skillName: SkillName): Skill {
            return Skill(skillName, SkillLevel.Competent)
        }

        public fun beginner(skillName: SkillName): Skill {
            return Skill(skillName, SkillLevel.Beginner)
        }
    }
}