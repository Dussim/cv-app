package xyz.dussim.feature.cvcontent

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import kotlinx.parcelize.Parcelize
import xyz.dussim.data.CvData
import xyz.dussim.designsystem.LocalScreenWidthClass
import xyz.dussim.designsystem.ScreenWidthClass
import xyz.dussim.navigation.ParcelableScreen

@Parcelize
data class CvContentScreen(
    private val cvData: CvData,
) : ParcelableScreen {
    override fun toString() = "CvContentScreen"

    @Composable
    override fun Content() {
        val screenWidthClass = LocalScreenWidthClass.current
        val screenModel = rememberScreenModel { CvContentScreenModel() }

        when (screenWidthClass) {
            ScreenWidthClass.Small -> SmallLayout(cvData, screenModel)
            ScreenWidthClass.Medium -> MediumLayout(cvData, screenModel)
            ScreenWidthClass.Big -> LargeLayout(cvData, screenModel)
        }
    }
}
