package xyz.dussim.feature.eastereggs.gym

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.parcelize.Parcelize
import xyz.dussim.navigation.ParcelableScreen

@Parcelize
internal data class GymStatsErrorScreen(val error: String) : ParcelableScreen {
    @Composable
    override fun Content() {
        LocalNavigator.currentOrThrow.pop() // TODO fix this someday
    }
}
