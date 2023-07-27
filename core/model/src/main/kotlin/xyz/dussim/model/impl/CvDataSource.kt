package xyz.dussim.model.impl

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import xyz.dussim.api.data.DataSource
import xyz.dussim.data.CvData
import xyz.dussim.data.about.AboutMe
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
    private val dispatcher: CoroutineDispatcher
) : DataSource<CvData> {

    override suspend fun fetch(): CvData = withContext(dispatcher) {
        CvData(
            skills = skillsDataSource.fetch(),
            languages = languagesDataSource.fetch(),
            aboutMe = aboutMeDataSource.fetch(),
            workplaces = workplacesDataSource.fetch(),
            socials = socialsDataSource.fetch()
        )
    }
}