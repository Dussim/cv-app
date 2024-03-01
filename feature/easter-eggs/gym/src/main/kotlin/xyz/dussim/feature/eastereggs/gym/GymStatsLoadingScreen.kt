package xyz.dussim.feature.eastereggs.gym

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.parcelize.Parcelize
import xyz.dussim.api.state.State
import xyz.dussim.apicompose.LocalActivityComponent
import xyz.dussim.designsystem.core.CvCircularProgressIndicator
import xyz.dussim.feature.ee.gym.R
import xyz.dussim.navigation.ParcelableScreen

@Parcelize
internal data object GymStatsLoadingScreen : ParcelableScreen {
    private fun readResolve(): Any = GymStatsLoadingScreen

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val unknown = stringResource(id = R.string.unknown)

        val gymStatsDataSource = LocalActivityComponent.current.networkComponent.gymStatsDataSource

        val model = rememberScreenModel { GymStatsModel(gymStatsDataSource) }

        val state by model.state.collectAsState()

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CvCircularProgressIndicator()
        }
        LaunchedEffect(state) {
            when (val gymStats = state) {
                State.Loading -> Unit
                is State.Error -> {
                    navigator.replace(GymStatsErrorScreen(gymStats.error.message ?: unknown))
                }
                is State.Success -> {
                    navigator.replace(GymStatsContentScreen(gymStats.value))
                }
            }
        }
    }
}
