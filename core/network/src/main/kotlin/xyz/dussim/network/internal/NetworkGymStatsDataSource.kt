package xyz.dussim.network.internal

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import xyz.dussim.api.data.DataSource
import xyz.dussim.api.state.State
import xyz.dussim.data.gym.GymStats
import xyz.dussim.network.internal.dto.GymStatsDto
import xyz.dussim.network.internal.dto.mapToGymStats

internal class NetworkGymStatsDataSource(
    private val endpointClient: EndpointClient,
    private val dispatcher: CoroutineDispatcher,
) : DataSource<State<List<GymStats>>> {
    override suspend fun fetch(): State<List<GymStats>> =
        withContext(dispatcher) {
            endpointClient
                .fetchGymStats()
                .map { gymStatsDtos -> gymStatsDtos.mapNotNull(GymStatsDto::mapToGymStats) }
                .map { State.success(it) }
                .getOrElse { State.error(it) }
        }
}
