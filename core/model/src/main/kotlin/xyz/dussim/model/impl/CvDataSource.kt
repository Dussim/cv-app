package xyz.dussim.model.impl

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import xyz.dussim.api.data.DataSource
import xyz.dussim.data.CvData
import xyz.dussim.data.about.AboutMe
import xyz.dussim.data.certificates.Certificate
import xyz.dussim.data.languages.Language
import xyz.dussim.data.skills.Skill
import xyz.dussim.data.socials.SocialLink
import xyz.dussim.data.workplace.Workplace

internal class CvDataSource(
    private val skillsDataSource: DataSource<List<Skill>>,
    private val languagesDataSource: DataSource<List<Language>>,
    private val aboutMeDataSource: DataSource<AboutMe>,
    private val workplacesDataSource: DataSource<List<Workplace>>,
    private val socialsDataSource: DataSource<List<SocialLink>>,
    private val certificatesDataSource: DataSource<List<Certificate>>,
    private val dispatcher: CoroutineDispatcher
) : DataSource<CvData> {

    override suspend fun fetch(): CvData = withContext(dispatcher) {
        val skills = async { skillsDataSource.fetch() }
        val languages = async { languagesDataSource.fetch() }
        val aboutMe = async { aboutMeDataSource.fetch() }
        val workplaces = async { workplacesDataSource.fetch() }
        val socials = async { socialsDataSource.fetch() }
        val certificates = async { certificatesDataSource.fetch() }

        CvData(
            skills = skills.await(),
            languages = languages.await(),
            aboutMe = aboutMe.await(),
            workplaces = workplaces.await(),
            socials = socials.await(),
            certificates = certificates.await()
        )
    }
}