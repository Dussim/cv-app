package xyz.dussim.model.impl

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import xyz.dussim.api.data.DataSource
import xyz.dussim.data.CvData
import xyz.dussim.data.ImmutableList
import xyz.dussim.data.about.AboutMe
import xyz.dussim.data.certificates.Certificate
import xyz.dussim.data.projects.Project
import xyz.dussim.data.socials.SocialLink
import xyz.dussim.data.workplace.Workplace
import xyz.dussim.datamodel.language.Language
import xyz.dussim.datamodel.skill.Skill

internal class CvDataSource(
    private val skillsDataSource: DataSource<List<Skill>>,
    private val languagesDataSource: DataSource<List<Language>>,
    private val aboutMeDataSource: DataSource<AboutMe>,
    private val workplacesDataSource: DataSource<List<Workplace>>,
    private val socialsDataSource: DataSource<List<SocialLink>>,
    private val certificatesDataSource: DataSource<List<Certificate>>,
    private val projectsDataSource: DataSource<List<Project>>,
    private val dispatcher: CoroutineDispatcher,
) : DataSource<CvData> {
    override suspend fun fetch(): CvData =
        withContext(dispatcher) {
            val skills = async { skillsDataSource.fetch() }
            val languages = async { languagesDataSource.fetch() }
            val aboutMe = async { aboutMeDataSource.fetch() }
            val workplaces = async { workplacesDataSource.fetch() }
            val socials = async { socialsDataSource.fetch() }
            val certificates = async { certificatesDataSource.fetch() }
            val projects = async { projectsDataSource.fetch() }

            CvData(
                skills = ImmutableList(skills.await()),
                languages = ImmutableList(languages.await()),
                aboutMe = aboutMe.await(),
                workplaces = ImmutableList(workplaces.await()),
                socials = ImmutableList(socials.await()),
                certificates = ImmutableList(certificates.await()),
                projects = ImmutableList(projects.await()),
            )
        }
}
