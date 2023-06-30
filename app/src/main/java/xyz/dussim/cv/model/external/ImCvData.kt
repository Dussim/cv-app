package xyz.dussim.cv.model.external

import androidx.compose.runtime.Immutable
import xyz.dussim.cv.data.ImList
import xyz.dussim.cv.data.toImmutable
import xyz.dussim.cv.model.external.about.AboutMe
import xyz.dussim.cv.model.external.languages.Language
import xyz.dussim.cv.model.external.skills.Skill
import xyz.dussim.cv.model.external.socials.SocialLink
import xyz.dussim.cv.model.external.workplace.WorkplaceData

@Immutable
data class ImCvData(
    val skills: ImList<Skill>,
    val languages: ImList<Language>,
    val socials: ImList<SocialLink>,
    val aboutMe: AboutMe,
    val workplaces: ImList<WorkplaceData>
) {
    constructor(cvData: CvData) : this(
        cvData.skills.toImmutable(),
        cvData.languages.toImmutable(),
        cvData.socials.toImmutable(),
        cvData.aboutMe,
        cvData.workplaces.toImmutable()
    )
}