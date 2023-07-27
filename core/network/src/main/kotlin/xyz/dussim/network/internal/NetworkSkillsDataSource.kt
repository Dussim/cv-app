package xyz.dussim.network.internal

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import xyz.dussim.api.data.DataSource
import xyz.dussim.api.state.State
import xyz.dussim.data.skills.Skill
import xyz.dussim.network.internal.dto.SkillDto
import xyz.dussim.network.internal.dto.mapToSkill

internal class NetworkSkillsDataSource(
    private val endpointClient: EndpointClient,
    private val dispatcher: CoroutineDispatcher
) : DataSource<State<List<Skill>>> {
    override suspend fun fetch(): State<List<Skill>> = withContext(dispatcher) {
        endpointClient
            .fetchSkills()
            .map { skillDtos -> skillDtos.mapNotNull(SkillDto::mapToSkill) }
            .map { State.Success(it) }
            .getOrElse { State.Error(it) }
    }
}