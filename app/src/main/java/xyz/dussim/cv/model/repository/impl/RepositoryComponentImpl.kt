package xyz.dussim.cv.model.repository.impl

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import xyz.dussim.cv.model.repository.AboutMeRepository
import xyz.dussim.cv.model.repository.AggregateRepository
import xyz.dussim.cv.model.repository.LanguagesRepository
import xyz.dussim.cv.model.repository.RepositoryComponent
import xyz.dussim.cv.model.repository.SkillsRepository
import xyz.dussim.cv.model.repository.SocialsRepository
import xyz.dussim.cv.model.repository.WorkplacesRepository
import xyz.dussim.httpclient.api.HttpComponent
import xyz.dussim.httpclient.impl.create

internal class RepositoryComponentImpl(
    private val httpComponent: HttpComponent = HttpComponent.create(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    override val socialsRepository: SocialsRepository = StaticSocialsRepository,
    override val languagesRepository: LanguagesRepository = StaticLanguagesRepository,
    override val skillsRepository: SkillsRepository = NetworkSkillsRepository(
        httpComponent.skillsApiEndpoint,
        StaticSkillsRepository
    ),
    override val aboutMeRepository: AboutMeRepository = StaticAboutMeRepository,
    override val workplacesRepository: WorkplacesRepository = StaticWorkplacesRepository
) : RepositoryComponent {
    override val aggregateRepository: AggregateRepository by lazy {
        AggregateRepositoryImpl(
            skillsRepository = skillsRepository,
            languagesRepository = languagesRepository,
            socialsRepository = socialsRepository,
            aboutMeRepository = aboutMeRepository,
            workplacesRepository = workplacesRepository,
            dispatcher = dispatcher
        )
    }
}
