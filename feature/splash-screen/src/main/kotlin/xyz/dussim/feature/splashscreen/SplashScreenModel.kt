package xyz.dussim.feature.splashscreen

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import xyz.dussim.api.data.DataSource
import xyz.dussim.api.state.State
import xyz.dussim.data.CvData

internal class SplashScreenModel(
    private val cvDataSource: DataSource<CvData>
) : StateScreenModel<State<CvData>>(State.loading()) {
    init {
        screenModelScope.launch {
            mutableState.value = State.success(cvDataSource.fetch())
        }
    }
}