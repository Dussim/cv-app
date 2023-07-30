package xyz.dussim.feature.cvcontent

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.rememberScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.parcelize.Parcelize
import xyz.dussim.data.CvData
import xyz.dussim.designsystem.LocalScreenWidthClass
import xyz.dussim.designsystem.ScreenWidthClass
import xyz.dussim.feature.cvcontent.model.internal.Tab
import xyz.dussim.feature.cvcontent.model.internal.TabModel
import xyz.dussim.navigation.ParcelableScreen
import xyz.dussim.resources.R


internal class CvContentScreenModel : ScreenModel {
    companion object {
        private val TABS = listOf(
            TabModel(Tab.All, null, R.string.button_tab_all),
            TabModel(Tab.Work, R.drawable.briefcase, R.string.button_tab_work_history),
            TabModel(Tab.Language, R.drawable.languages, R.string.button_tab_languages),
            TabModel(Tab.Skills, R.drawable.brain, R.string.button_tab_skills),
            TabModel(Tab.Certificates, R.drawable.certificate, R.string.button_tab_certificates)
        )
    }

    private val _selectedTab = MutableStateFlow(Tab.All)
    val selectedTab = _selectedTab.asStateFlow()

    private val _tabs = MutableStateFlow(TABS)
    val tabs = _tabs.asStateFlow()

    fun onTabSelected(tab: Tab) {
        _selectedTab.value = tab
    }
}

@Parcelize
class CvContentScreen(
    private val cvData: CvData
) : ParcelableScreen {

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