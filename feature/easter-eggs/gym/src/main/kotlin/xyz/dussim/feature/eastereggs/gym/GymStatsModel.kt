package xyz.dussim.feature.eastereggs.gym

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import xyz.dussim.api.data.DataSource
import xyz.dussim.api.state.State
import xyz.dussim.data.gym.GymStats

internal class GymStatsModel(
    private val gymStatsDataSource: DataSource<State<List<GymStats>>>,
) : StateScreenModel<State<List<GymStats>>>(State.Loading) {
    init {
        screenModelScope.launch {
            mutableState.value = gymStatsDataSource.fetch()
        }
    }
}
