package xyz.dussim.network.internal

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import xyz.dussim.api.data.DataSource
import xyz.dussim.api.state.State
import xyz.dussim.datamodel.UniversalMapper
import xyz.dussim.datamodel.skill.Skill

internal class NetworkSkillsDataSource(
    private val endpointClient: EndpointClient,
    private val universalMapper: UniversalMapper,
    private val dispatcher: CoroutineDispatcher,
) : DataSource<State<List<Skill>>> {
    override suspend fun fetch(): State<List<Skill>> =
        withContext(dispatcher) {
            endpointClient
                .fetchSkills()
                .map(universalMapper::mapSkills)
                .map { State.Success(it) }
                .getOrElse { State.Error(it) }
        }
}
