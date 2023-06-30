package xyz.dussim.cv.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import xyz.dussim.cv.R
import xyz.dussim.cv.data.toImmutable
import xyz.dussim.cv.model.external.ImCvData
import xyz.dussim.cv.model.external.about.AboutMe
import xyz.dussim.cv.model.external.skills.Skill
import xyz.dussim.cv.model.external.socials.SocialLink
import xyz.dussim.cv.model.internal.Tab
import xyz.dussim.cv.model.internal.Tab.All
import xyz.dussim.cv.model.internal.Tab.Certificates
import xyz.dussim.cv.model.internal.Tab.Language
import xyz.dussim.cv.model.internal.Tab.Skills
import xyz.dussim.cv.model.internal.Tab.Work
import xyz.dussim.cv.model.internal.TabModel
import xyz.dussim.cv.model.repository.AggregateRepository

private val TABS = listOf(
    TabModel(All, null, R.string.button_tab_all),
    TabModel(Work, R.drawable.briefcase, R.string.button_tab_work_history),
    TabModel(Language, R.drawable.languages, R.string.button_tab_languages),
    TabModel(Skills, R.drawable.brain, R.string.button_tab_skills),
    TabModel(Certificates, R.drawable.certificate, R.string.button_tab_certificates),
).toImmutable()

class MainViewModel(
    private val aggregateRepository: AggregateRepository
) : ViewModel() {
    private val _selectedTab = MutableStateFlow(All)
    val selectedTab = _selectedTab.asStateFlow()

    private val _tabs = MutableStateFlow(TABS)
    val tabs = _tabs.asStateFlow()

    private val _languages = MutableStateFlow(emptyList<xyz.dussim.cv.model.external.languages.Language>().toImmutable())
    val languages = _languages.asStateFlow()

    private val _skills = MutableStateFlow(emptyList<Skill>().toImmutable())
    val skills = _skills.asStateFlow()

    private val _socials = MutableStateFlow(emptyList<SocialLink>().toImmutable())
    val socials = _socials.asStateFlow()

    private val _aboutMe = MutableStateFlow<AboutMe?>(null)
    val aboutMe = _aboutMe.asStateFlow()

    private val _imCvData = MutableStateFlow<ImCvData?>(null)
    val imCvData = _imCvData.asStateFlow()

    init {
        viewModelScope.launch {
            val cvData = aggregateRepository.fetchAggregatedData()
            val imCvData = ImCvData(cvData)

            _imCvData.value = imCvData

            _languages.value = imCvData.languages
            _skills.value = imCvData.skills
            _socials.value = imCvData.socials
            _aboutMe.value = imCvData.aboutMe
        }
    }

    fun onTabSelected(tab: Tab) {
        _selectedTab.value = tab
    }

    fun isReady(): Boolean {
        return imCvData.value != null
    }
}