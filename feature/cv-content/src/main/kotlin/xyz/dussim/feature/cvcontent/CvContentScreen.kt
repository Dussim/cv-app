package xyz.dussim.feature.cvcontent

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.rememberScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.parcelize.Parcelize
import xyz.dussim.data.CvData
import xyz.dussim.data.about.AboutMe
import xyz.dussim.data.languages.Language
import xyz.dussim.data.skills.Skill
import xyz.dussim.data.socials.SocialLink
import xyz.dussim.data.workplace.Workplace
import xyz.dussim.designsystem.H3
import xyz.dussim.designsystem.LocalScreenWidthClass
import xyz.dussim.designsystem.ScreenWidthClass
import xyz.dussim.feature.cvcontent.components.*
import xyz.dussim.feature.cvcontent.model.internal.Tab
import xyz.dussim.feature.cvcontent.model.internal.TabModel
import xyz.dussim.navigation.ParcelableScreen
import xyz.dussim.resources.R

private fun ScreenWidthClass.toSocialLinksOrientation() = when (this) {
    ScreenWidthClass.Small -> Orientation.Column
    ScreenWidthClass.Medium -> Orientation.Row
    ScreenWidthClass.Big -> Orientation.Column
}

private fun ScreenWidthClass.toButtonsOrientation() = when (this) {
    ScreenWidthClass.Small -> Orientation.Column
    else -> Orientation.Row
}

private fun ScreenWidthClass.toContentPadding() = when (this) {
    ScreenWidthClass.Small -> 10.dp
    ScreenWidthClass.Medium -> 20.dp
    ScreenWidthClass.Big -> 20.dp
}

private fun ScreenWidthClass.toContactHeaderContentPadding() = when (this) {
    ScreenWidthClass.Small -> PaddingValues(20.dp)
    else -> PaddingValues(40.dp)
}


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
        val screenModel = rememberScreenModel { CvContentScreenModel() }

        val tabs by screenModel.tabs.collectAsState()
        val selectedTab by screenModel.selectedTab.collectAsState()

        ScreenLayout(
            screenWidthClass = LocalScreenWidthClass.current,
            selectedTab = selectedTab,
            tabs = tabs,
            imCvData = cvData
        )
    }

    @Composable
    private fun ScreenLayout(
        screenWidthClass: ScreenWidthClass,
        selectedTab: Tab,
        tabs: List<TabModel>,
        imCvData: CvData
    ) {
        when (screenWidthClass) {
            ScreenWidthClass.Small, ScreenWidthClass.Medium -> MediumAndBelowScreenLayout(
                screenWidthClass = screenWidthClass,
                selectedTab = selectedTab,
                tabs = tabs,
                socialLinks = imCvData.socials,
                aboutMe = imCvData.aboutMe,
                languages = imCvData.languages,
                skills = imCvData.skills,
                workplaces = imCvData.workplaces
            )

            ScreenWidthClass.Big -> LargeScreenLayout(
                screenWidthClass = screenWidthClass,
                selectedTab = selectedTab,
                tabs = tabs,
                socialLinks = imCvData.socials,
                aboutMe = imCvData.aboutMe,
                languages = imCvData.languages,
                skills = imCvData.skills,
                workplaces = imCvData.workplaces
            )
        }
    }

    @Composable
    private fun MediumAndBelowScreenLayout(
        screenWidthClass: ScreenWidthClass,
        selectedTab: Tab,
        tabs: List<TabModel>,
        socialLinks: List<SocialLink>,
        aboutMe: AboutMe,
        languages: List<Language>,
        skills: List<Skill>,
        workplaces: List<Workplace>
    ) {
        xyz.dussim.designsystem.core.HidingHeader(
            contentPadding = PaddingValues(screenWidthClass.toContentPadding()),
            header = {
                HeaderColumn(screenWidthClass, socialLinks, aboutMe)
            }
        ) { scrollState ->
            // TODO make it its own modifier that will be easy to apply and reuse
//            val scrollFraction by remember {
//                derivedStateOf {
//                    (scrollState.value.toFloat() / (scrollState.maxValue / 4)).coerceAtMost(1f)
//                }
//            }

            val padding = when (screenWidthClass) {
                ScreenWidthClass.Small -> 0.dp
                ScreenWidthClass.Medium -> 20.dp
                ScreenWidthClass.Big -> 40.dp
            }
            val backgroundColor = if (screenWidthClass != ScreenWidthClass.Small) {
                Color(0x1C7F8BBE)
            } else {
                Color.Transparent
            }

            Column(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .background(backgroundColor, RoundedCornerShape(16.dp))
                    .padding(padding)
            ) {
                TopBar(selectedTab, tabs)
                Spacer(modifier = Modifier.height(30.dp))
                Box(
//                    modifier = Modifier.background(
//                        Brush.verticalGradient(
//                            0f to Color(0xFF1C2344).copy(alpha = scrollFraction),
//                            0.1f to Color.Transparent,
//                            0.9f to Color.Transparent,
//                            1f to Color(0xFF1C2344).copy(alpha = 1f - scrollFraction)
//                        )
//                    )
                ) {
                    SelectedTabContent(
                        selectedTab = selectedTab,
                        screenWidthClass = screenWidthClass,
                        languages = languages,
                        skills = skills,
                        workplaces = workplaces,
                        modifier = Modifier.verticalScroll(scrollState)
                    )
                }
            }
        }
    }

    @Composable
    private fun LargeScreenLayout(
        screenWidthClass: ScreenWidthClass,
        selectedTab: Tab,
        tabs: List<TabModel>,
        socialLinks: List<SocialLink>,
        aboutMe: AboutMe,
        languages: List<Language>,
        skills: List<Skill>,
        workplaces: List<Workplace>
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .fillMaxHeight()
                    .widthIn(max = 1440.dp)
                    .align(Alignment.TopCenter)
            ) {
                HeaderColumn(
                    screenWidthClass = screenWidthClass,
                    socialLinks = socialLinks,
                    aboutMe = aboutMe,
                    modifier = Modifier.weight(1f)
                )
                Card(screenWidthClass = screenWidthClass, modifier = Modifier.weight(2f)) {
                    Column {
                        TopBar(selectedTab, tabs)
                        Spacer(Modifier.height(20.dp))
                        SelectedTabContent(
                            selectedTab = selectedTab,
                            screenWidthClass = screenWidthClass,
                            languages = languages,
                            skills = skills,
                            workplaces = workplaces,
                            modifier = Modifier.verticalScroll(rememberScrollState())
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun HeaderColumn(
        screenWidthClass: ScreenWidthClass,
        socialLinks: List<SocialLink>,
        aboutMe: AboutMe,
        modifier: Modifier = Modifier
    ) {
        Column(
            modifier,
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            ContactHeader(
                socialLinksOrientation = screenWidthClass.toSocialLinksOrientation(),
                buttonsOrientation = screenWidthClass.toButtonsOrientation(),
                contentPadding = screenWidthClass.toContactHeaderContentPadding(),
                imageRow = contactInfoPhotoHeader(screenWidthClass),
                socialLinks = socialLinks
            )
            AboutSection(aboutMe = aboutMe)
        }
    }

    @Composable
    private fun TopBar(
        selectedTab: Tab,
        tabs: List<TabModel>,
        modifier: Modifier = Modifier
    ) {
        val screenModel = rememberScreenModel { CvContentScreenModel() }//TODO
        Column {
            Row(
                modifier = modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp) // FIXME
            ) {
                tabs.forEach { model ->
                    CategoryTab(
                        tabModel = model,
                        checked = selectedTab == model.tab,
                        onSelected = screenModel::onTabSelected
                    )
                }
            }
        }
    }

    @Composable
    private fun CategoryTab(
        tabModel: TabModel,
        checked: Boolean,
        modifier: Modifier = Modifier,
        onSelected: (Tab) -> Unit
    ) {
        xyz.dussim.designsystem.core.CVOutlinedToggleButton(
            checked = checked,
            onCheckedChange = { selected ->
                if (selected) {
                    onSelected(tabModel.tab)
                }
            },
            modifier = modifier
        ) {
            if (tabModel.iconRes != null) {
                xyz.dussim.designsystem.core.CvIcon(vectorRes = tabModel.iconRes, contentDescription = "todo")
                Spacer(modifier = Modifier.width(10.dp))
            }
            Box(modifier = Modifier.heightIn(min = 24.dp), contentAlignment = Alignment.Center) {
                BasicText(text = stringResource(id = tabModel.textRes), style = H3)
            }
        }
    }

    @Composable
    private fun ContentColumn(
        modifier: Modifier = Modifier,
        verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(30.dp), // FIXME
        horizontalAlignment: Alignment.Horizontal = Alignment.Start,
        content: @Composable ColumnScope.() -> Unit
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment
        ) {
            content()
            ConsentFooter(Modifier.padding(top = 20.dp)) // FIXME
        }
    }

    @Composable
    private fun WorkPeriodFor(screenWidthClass: ScreenWidthClass, workplaces: List<Workplace>) {
        when (screenWidthClass) {
            ScreenWidthClass.Small, ScreenWidthClass.Medium -> WorkPeriodVertical(workplaces = workplaces)
            ScreenWidthClass.Big -> WorkPeriodHorizontal(workplaces = workplaces)
        }
    }

    @Composable
    private fun CertificatesFor(screenWidthClass: ScreenWidthClass) {
        when (screenWidthClass) {
            ScreenWidthClass.Small -> CertificatesVertical()
            else -> CertificatesHorizontal()
        }
    }

    @Composable
    private fun CombinedSections(
        screenWidthClass: ScreenWidthClass,
        languages: List<Language>,
        skills: List<Skill>,
        workplaces: List<Workplace>
    ) {
        WorkPeriodFor(screenWidthClass, workplaces)
        LanguagesColumn(languages = languages)
        SkillsColumn(skills = skills)
        CertificatesFor(screenWidthClass)
    }

    @Composable
    private fun SelectedTabContent(
        selectedTab: Tab,
        screenWidthClass: ScreenWidthClass,
        languages: List<Language>,
        skills: List<Skill>,
        workplaces: List<Workplace>,
        modifier: Modifier = Modifier
    ) {
        ContentColumn(modifier) {
            when (selectedTab) {
                Tab.All -> CombinedSections(screenWidthClass, languages, skills, workplaces)
                Tab.Work -> WorkPeriodFor(screenWidthClass, workplaces)
                Tab.Language -> LanguagesColumn(languages = languages)
                Tab.Skills -> SkillsColumn(skills = skills)
                Tab.Certificates -> CertificatesFor(screenWidthClass)
            }
        }
    }

    @Composable
    private fun Card(
        modifier: Modifier = Modifier,
        screenWidthClass: ScreenWidthClass,
        content: @Composable () -> Unit
    ) {
        Box(
            modifier = Modifier
                .background(Color(0x1C7F8BBE), RoundedCornerShape(16.dp))
                .padding(
                    when (screenWidthClass) {
                        ScreenWidthClass.Small -> 0.dp
                        ScreenWidthClass.Medium -> 20.dp
                        ScreenWidthClass.Big -> 40.dp
                    }
                )
                .then(modifier)
        ) {
            content()
        }
    }
}