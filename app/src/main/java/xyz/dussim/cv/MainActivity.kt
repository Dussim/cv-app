package xyz.dussim.cv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import xyz.dussim.api.skills.Skill
import xyz.dussim.cv.data.ImList
import xyz.dussim.cv.data.LocalTextStyleProvider
import xyz.dussim.cv.data.TextStyleProvider
import xyz.dussim.cv.model.MainViewModel
import xyz.dussim.cv.model.external.ImCvData
import xyz.dussim.cv.model.external.about.AboutMe
import xyz.dussim.cv.model.external.languages.Language
import xyz.dussim.cv.model.external.socials.SocialLink
import xyz.dussim.cv.model.external.workplace.WorkplaceData
import xyz.dussim.cv.model.internal.Tab
import xyz.dussim.cv.model.internal.TabModel
import xyz.dussim.cv.model.repository.AggregateRepository
import xyz.dussim.cv.model.repository.RepositoryComponent
import xyz.dussim.cv.model.repository.impl.create
import xyz.dussim.cv.ui.components.AboutSection
import xyz.dussim.cv.ui.components.CertificatesHorizontal
import xyz.dussim.cv.ui.components.CertificatesVertical
import xyz.dussim.cv.ui.components.ConsentFooter
import xyz.dussim.cv.ui.components.ContactHeader
import xyz.dussim.cv.ui.components.LanguagesColumn
import xyz.dussim.cv.ui.components.Orientation
import xyz.dussim.cv.ui.components.SkillsColumn
import xyz.dussim.cv.ui.components.WorkPeriodHorizontal
import xyz.dussim.cv.ui.components.WorkPeriodVertical
import xyz.dussim.cv.ui.components.contactInfoPhotoHeader
import xyz.dussim.cv.ui.components.core.CVOutlinedToggleButton
import xyz.dussim.cv.ui.components.core.CvIcon
import xyz.dussim.cv.ui.components.core.HidingHeader
import xyz.dussim.cv.ui.theme.H3
import xyz.dussim.cv.ui.utils.ScreenWidthClass
import xyz.dussim.cv.ui.utils.ScreenWidthClass.Big
import xyz.dussim.cv.ui.utils.ScreenWidthClass.Medium
import xyz.dussim.cv.ui.utils.ScreenWidthClass.Small
import xyz.dussim.cv.ui.utils.waitBeforeDrawing

private fun ScreenWidthClass.toSocialLinksOrientation() = when (this) {
    Small -> Orientation.Column
    Medium -> Orientation.Row
    Big -> Orientation.Column
}

private fun ScreenWidthClass.toButtonsOrientation() = when (this) {
    Small -> Orientation.Column
    else -> Orientation.Row
}

private fun ScreenWidthClass.toContentPadding() = when (this) {
    Small -> 10.dp
    Medium -> 20.dp
    Big -> 20.dp
}

private fun ScreenWidthClass.toContactHeaderContentPadding() = when (this) {
    Small -> PaddingValues(20.dp)
    else -> PaddingValues(40.dp)
}

private fun ScreenWidthClass.toTextStyleProvider() = when (this) {
    Small -> TextStyleProvider.Default
    else -> TextStyleProvider.Big
}

object AggregateRepositoryKey : CreationExtras.Key<AggregateRepository>

class MainActivity : ComponentActivity() {
    private val repositoryComponent by lazy { RepositoryComponent.create() }

    private val creationExtras by lazy {
        MutableCreationExtras(defaultViewModelCreationExtras).apply {
            this[AggregateRepositoryKey] = repositoryComponent.aggregateRepository
        }
    }

    private val viewModelFactory = viewModelFactory {
        initializer {
            val aggregateRepository = checkNotNull(this[AggregateRepositoryKey]) {
                "AggregateRepository was not provided for the viewModel instance"
            }
            MainViewModel(aggregateRepository)
        }
    }

    private val viewModel by viewModels<MainViewModel>(
        extrasProducer = { creationExtras },
        factoryProducer = { viewModelFactory }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        waitBeforeDrawing(until = viewModel::isReady)

        setContent {
            val tabs by viewModel.tabs.collectAsState()
            val selectedTab by viewModel.selectedTab.collectAsState()
            val imCvData by viewModel.imCvData.collectAsState()

            val screenWidthClass = ScreenWidthClass.calculateFor(activity = this)

            val uiController = rememberSystemUiController()

            DisposableEffect(uiController) {
                uiController.setSystemBarsColor(Color.Transparent)
                onDispose { }
            }

            imCvData?.let {
                CompositionLocalProvider(
                    LocalTextStyleProvider.provides(screenWidthClass.toTextStyleProvider())
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                Brush.linearGradient(
                                    0f to Color(17, 23, 55),
                                    0.5f to Color(22, 42, 108),
                                    1f to Color(22, 38, 91),
                                )
                            )
                            .safeDrawingPadding()
                            .clipToBounds()
                    ) {
                        ScreenLayout(
                            screenWidthClass = screenWidthClass,
                            selectedTab = selectedTab,
                            tabs = tabs,
                            imCvData = it
                        )
                    }
                }
            }

        }
    }

    @Composable
    private fun ScreenLayout(
        screenWidthClass: ScreenWidthClass,
        selectedTab: Tab,
        tabs: ImList<TabModel>,
        imCvData: ImCvData
    ) {
        when (screenWidthClass) {
            Small, Medium -> MediumAndBelowScreenLayout(
                screenWidthClass = screenWidthClass,
                selectedTab = selectedTab,
                tabs = tabs,
                socialLinks = imCvData.socials,
                aboutMe = imCvData.aboutMe,
                languages = imCvData.languages,
                skills = imCvData.skills,
                workplaces = imCvData.workplaces
            )

            Big -> LargeScreenLayout(
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
        tabs: ImList<TabModel>,
        socialLinks: ImList<SocialLink>,
        aboutMe: AboutMe,
        languages: ImList<Language>,
        skills: ImList<Skill>,
        workplaces: ImList<WorkplaceData>
    ) {
        HidingHeader(
            contentPadding = PaddingValues(screenWidthClass.toContentPadding()),
            header = {
                HeaderColumn(screenWidthClass, socialLinks, aboutMe)
            }
        ) { scrollState ->
            //TODO make it its own modifier that will be easy to apply and reuse
//            val scrollFraction by remember {
//                derivedStateOf {
//                    (scrollState.value.toFloat() / (scrollState.maxValue / 4)).coerceAtMost(1f)
//                }
//            }

            val padding = when (screenWidthClass) {
                Small -> 0.dp
                Medium -> 20.dp
                Big -> 40.dp
            }
            val backgroundColor = if (screenWidthClass != Small) {
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
        tabs: ImList<TabModel>,
        socialLinks: ImList<SocialLink>,
        aboutMe: AboutMe,
        languages: ImList<Language>,
        skills: ImList<Skill>,
        workplaces: ImList<WorkplaceData>
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
        socialLinks: ImList<SocialLink>,
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
        tabs: ImList<TabModel>,
        modifier: Modifier = Modifier
    ) {
        Column {
            Row(
                modifier = modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp),//FIXME
            ) {
                tabs.forEach { model ->
                    CategoryTab(
                        tabModel = model,
                        checked = selectedTab == model.tab,
                        onSelected = viewModel::onTabSelected
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
        CVOutlinedToggleButton(
            checked = checked,
            onCheckedChange = { selected ->
                if (selected) {
                    onSelected(tabModel.tab)
                }
            },
            modifier = modifier
        ) {
            if (tabModel.iconRes != null) {
                CvIcon(vectorRes = tabModel.iconRes, contentDescription = "todo")
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
        verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(30.dp),//FIXME
        horizontalAlignment: Alignment.Horizontal = Alignment.Start,
        content: @Composable ColumnScope.() -> Unit
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment
        ) {
            content()
            ConsentFooter(Modifier.padding(top = 20.dp))//FIXME
        }
    }

    @Composable
    private fun WorkPeriodFor(screenWidthClass: ScreenWidthClass, workplaces: ImList<WorkplaceData>) {
        when (screenWidthClass) {
            Small, Medium -> WorkPeriodVertical(workplaces = workplaces)
            Big -> WorkPeriodHorizontal(workplaces = workplaces)
        }
    }

    @Composable
    private fun CertificatesFor(screenWidthClass: ScreenWidthClass) {
        when (screenWidthClass) {
            Small -> CertificatesVertical()
            else -> CertificatesHorizontal()
        }
    }

    @Composable
    private fun CombinedSections(
        screenWidthClass: ScreenWidthClass,
        languages: ImList<Language>,
        skills: ImList<Skill>,
        workplaces: ImList<WorkplaceData>
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
        languages: ImList<Language>,
        skills: ImList<Skill>,
        workplaces: ImList<WorkplaceData>,
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
                        Small -> 0.dp
                        Medium -> 20.dp
                        Big -> 40.dp
                    }
                )
                .then(modifier)
        ) {
            content()
        }
    }
}