package xyz.dussim.cv.model.external

import androidx.compose.runtime.Immutable
import xyz.dussim.cv.data.ImList
import xyz.dussim.cv.data.toImmutable
import xyz.dussim.data.CvData
import xyz.dussim.data.about.AboutMe
import xyz.dussim.data.languages.Language
import xyz.dussim.data.skills.Skill
import xyz.dussim.data.socials.SocialLink
import xyz.dussim.data.workplace.Workplace

@Immutable
data class ImCvData(
    val skills: ImList<Skill>,
    val languages: ImList<Language>,
    val socials: ImList<SocialLink>,
    val aboutMe: AboutMe,
    val workplaces: ImList<Workplace>
) {
    constructor(cvData: CvData) : this(
        cvData.skills.toImmutable(),
        cvData.languages.toImmutable(),
        cvData.socials.toImmutable(),
        cvData.aboutMe,
        cvData.workplaces.toImmutable()
    )
}
