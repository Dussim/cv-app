package xyz.dussim.feature.splashscreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.parcelize.Parcelize
import xyz.dussim.api.state.State
import xyz.dussim.apicompose.LocalAppComponent
import xyz.dussim.navigation.CvAppScreens
import xyz.dussim.navigation.ParcelableScreen

@Parcelize
internal data object SplashScreen : ParcelableScreen {
    private fun readResolve(): Any = SplashScreen

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val cvDataSource = LocalAppComponent.current.modelComponent.cvDataSource

        val model = rememberScreenModel { SplashScreenModel(cvDataSource) }

        val state by model.state.collectAsState()

        when (val cvData = state) {
            State.Loading, is State.Error -> Unit //TODO handle error
            is State.Success -> {
                val screen = rememberScreen(CvAppScreens.CvContent(cvData.value))
                LaunchedEffect(screen) { navigator.replaceAll(screen) }
            }
        }
    }
}

