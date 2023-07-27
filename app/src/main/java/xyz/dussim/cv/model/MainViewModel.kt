package xyz.dussim.cv.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import xyz.dussim.api.data.DataSource
import xyz.dussim.cv.data.toImmutable
import xyz.dussim.cv.model.external.ImCvData
import xyz.dussim.cv.model.internal.Tab
import xyz.dussim.cv.model.internal.Tab.*
import xyz.dussim.cv.model.internal.TabModel
import xyz.dussim.data.CvData
import xyz.dussim.resources.R

private val TABS = listOf(
    TabModel(All, null, R.string.button_tab_all),
    TabModel(Work, R.drawable.briefcase, R.string.button_tab_work_history),
    TabModel(Language, R.drawable.languages, R.string.button_tab_languages),
    TabModel(Skills, R.drawable.brain, R.string.button_tab_skills),
    TabModel(Certificates, R.drawable.certificate, R.string.button_tab_certificates),
).toImmutable()

class MainViewModel(
    private val cvDataSource: DataSource<CvData>
) : ViewModel() {
    private val _selectedTab = MutableStateFlow(All)
    val selectedTab = _selectedTab.asStateFlow()

    private val _tabs = MutableStateFlow(TABS)
    val tabs = _tabs.asStateFlow()

    private val _imCvData = MutableStateFlow<ImCvData?>(null)
    val imCvData = _imCvData.asStateFlow()

    init {
        viewModelScope.launch {
            val cvData = cvDataSource.fetch()
            val imCvData = ImCvData(cvData)

            _imCvData.value = imCvData
        }
    }

    fun onTabSelected(tab: Tab) {
        _selectedTab.value = tab
    }

    fun isReady(): Boolean {
        return imCvData.value != null
    }
}