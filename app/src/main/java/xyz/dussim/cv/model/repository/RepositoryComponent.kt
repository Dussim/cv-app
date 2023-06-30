package xyz.dussim.cv.model.repository

interface RepositoryComponent {
    companion object

    val socialsRepository: SocialsRepository
    val languagesRepository: LanguagesRepository
    val skillsRepository: SkillsRepository
    val aboutMeRepository: AboutMeRepository
    val workplacesRepository: WorkplacesRepository

    val aggregateRepository: AggregateRepository
}