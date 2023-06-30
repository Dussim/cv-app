package xyz.dussim.cv.model.repository.impl

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import xyz.dussim.cv.model.external.CvData
import xyz.dussim.cv.model.repository.AboutMeRepository
import xyz.dussim.cv.model.repository.AggregateRepository
import xyz.dussim.cv.model.repository.LanguagesRepository
import xyz.dussim.cv.model.repository.SkillsRepository
import xyz.dussim.cv.model.repository.SocialsRepository
import xyz.dussim.cv.model.repository.WorkplacesRepository

internal class AggregateRepositoryImpl(
    private val skillsRepository: SkillsRepository,
    private val languagesRepository: LanguagesRepository,
    private val socialsRepository: SocialsRepository,
    private val aboutMeRepository: AboutMeRepository,
    private val workplacesRepository: WorkplacesRepository,
    private val dispatcher: CoroutineDispatcher
) : AggregateRepository {
    override suspend fun fetchAggregatedData(): CvData = withContext(dispatcher) {
        val skills = async { skillsRepository.fetchSkills() }
        val languages = async { languagesRepository.fetchLanguages() }
        val socials = async { socialsRepository.fetchSocials() }
        val aboutMe = async { aboutMeRepository.fetchAboutMe() }
        val workplaces = async { workplacesRepository.fetchWorkplaces() }

        CvData(
            skills = skills.await(),
            languages = languages.await(),
            socials = socials.await(),
            aboutMe = aboutMe.await(),
            workplaces = workplaces.await()
        )
    }
}