package xyz.dussim.model.impl.fakes

import kotlinx.coroutines.CoroutineDispatcher
import xyz.dussim.api.components.LocalComponent
import xyz.dussim.api.components.NetworkComponent
import xyz.dussim.api.coroutines.DispatchersComponent
import xyz.dussim.api.data.DataSource
import xyz.dussim.api.state.State
import xyz.dussim.data.about.AboutMe
import xyz.dussim.data.certificates.Certificate
import xyz.dussim.data.gym.GymStats
import xyz.dussim.data.projects.Project
import xyz.dussim.data.socials.SocialLink
import xyz.dussim.data.workplace.Workplace
import xyz.dussim.datamodel.language.Language
import xyz.dussim.datamodel.skill.Skill

/**
 * Fake component implementations for testing.
 *
 * A fake dispatchers component that uses the provided test dispatcher for all dispatchers.
 */
class FakeDispatchersComponent(private val testDispatcher: CoroutineDispatcher) : DispatchersComponent {
    override val main: CoroutineDispatcher = testDispatcher
    override val io: CoroutineDispatcher = testDispatcher
    override val default: CoroutineDispatcher = testDispatcher
    override val unconfined: CoroutineDispatcher = testDispatcher
}

/**
 * A fake local component that returns the provided data for each data source.
 */
class FakeLocalComponent(
    skills: List<Skill>,
    languages: List<Language>,
    aboutMe: AboutMe,
    workplaces: List<Workplace>,
    socials: List<SocialLink>,
    certificates: List<Certificate>,
    projects: List<Project>,
) : LocalComponent {
    override val skillsDataSource: DataSource<List<Skill>> = FakeDataSource(skills)
    override val languagesDataSource: DataSource<List<Language>> = FakeDataSource(languages)
    override val aboutMeDataSource: DataSource<AboutMe> = FakeDataSource(aboutMe)
    override val workplacesDataSource: DataSource<List<Workplace>> = FakeDataSource(workplaces)
    override val socialMediaDataSource: DataSource<List<SocialLink>> = FakeDataSource(socials)
    override val certificatesDataSource: DataSource<List<Certificate>> = FakeDataSource(certificates)
    override val projectsDataSource: DataSource<List<Project>> = FakeDataSource(projects)
}

/**
 * A fake network component that returns the provided data for each data source.
 */
class FakeNetworkComponent(
    skills: List<Skill>,
    languages: List<Language>,
) : NetworkComponent {
    override val skillsDataSource: DataSource<State<List<Skill>>> = FakeSuccessNetworkDataSource(skills)
    override val leanguagesDataSource: DataSource<State<List<Language>>> = FakeSuccessNetworkDataSource(languages)
    override val gymStatsDataSource: DataSource<State<List<GymStats>>> = FakeDataSource(State.Error(RuntimeException("Not implemented")))
}
