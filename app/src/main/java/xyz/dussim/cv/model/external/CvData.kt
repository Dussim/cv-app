package xyz.dussim.cv.model.external

import androidx.compose.runtime.Immutable
import xyz.dussim.cv.model.external.about.AboutMe
import xyz.dussim.cv.model.external.languages.Language
import xyz.dussim.cv.model.external.skills.Skill
import xyz.dussim.cv.model.external.socials.SocialLink
import xyz.dussim.cv.model.external.workplace.WorkplaceData

@Immutable
data class CvData(
    val skills: List<Skill>,
    val languages: List<Language>,
    val socials: List<SocialLink>,
    val aboutMe: AboutMe,
    val workplaces: List<WorkplaceData>
)
