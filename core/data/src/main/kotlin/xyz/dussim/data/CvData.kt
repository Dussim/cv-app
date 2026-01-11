package xyz.dussim.data

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import xyz.dussim.data.about.AboutMe
import xyz.dussim.data.certificates.Certificate
import xyz.dussim.data.projects.Project
import xyz.dussim.data.socials.SocialLink
import xyz.dussim.data.workplace.Workplace
import xyz.dussim.datamodel.language.Language
import xyz.dussim.datamodel.skill.Skill

@Immutable
@Parcelize
class ImmutableList<E : Parcelable>(
    private val list: List<E>,
) : List<E> by list,
    Parcelable

@Immutable
@Parcelize
data class CvData(
    val skills: ImmutableList<Skill>,
    val languages: ImmutableList<Language>,
    val socials: ImmutableList<SocialLink>,
    val aboutMe: AboutMe,
    val workplaces: ImmutableList<Workplace>,
    val certificates: ImmutableList<Certificate>,
    val projects: ImmutableList<Project>,
) : Parcelable
