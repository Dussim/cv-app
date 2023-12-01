package xyz.dussim.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import xyz.dussim.data.about.AboutMe
import xyz.dussim.data.certificates.Certificate
import xyz.dussim.data.languages.Language
import xyz.dussim.data.skills.Skill
import xyz.dussim.data.socials.SocialLink
import xyz.dussim.data.workplace.Workplace

@Parcelize
data class CvData(
    val skills: List<Skill>,
    val languages: List<Language>,
    val socials: List<SocialLink>,
    val aboutMe: AboutMe,
    val workplaces: List<Workplace>,
    val certificates: List<Certificate>
) : Parcelable