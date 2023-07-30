package xyz.dussim.feature.cvcontent

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import xyz.dussim.data.CvData
import xyz.dussim.designsystem.*
import xyz.dussim.feature.cvcontent.components.*
import xyz.dussim.feature.cvcontent.model.internal.Tab
import xyz.dussim.feature.cvcontent.model.internal.TabModel

@Composable
internal fun LargeLayout(cvData: CvData, screenModel: CvContentScreenModel) {
    val tabs by screenModel.tabs.collectAsState()
    val selectedTab by screenModel.selectedTab.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(margin_2_5x)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(margin_2_5x),
            modifier = Modifier
                .fillMaxHeight()
                .widthIn(max = 1440.dp)
                .align(Alignment.TopCenter)
        ) {
            HeaderColumn(
                modifier = Modifier.weight(1f),
                cvData = cvData
            )
            Card(modifier = Modifier.weight(2f)) {
                Column {
                    TopBar(
                        tabs = tabs,
                        selectedTab = selectedTab,
                        onTabSelected = screenModel::onTabSelected
                    )
                    Spacer(Modifier.height(margin_2_5x))
                    SelectedTabContent(
                        modifier = Modifier.verticalScroll(rememberScrollState()),
                        selectedTab = selectedTab,
                        cvData = cvData
                    )
                }
            }
        }
    }
}

@Composable
private fun HeaderColumn(
    modifier: Modifier = Modifier,
    cvData: CvData
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.spacedBy(margin_4x),
    ) {
        ContactHeader(
            socialLinksOrientation = Orientation.Column,
            buttonsOrientation = Orientation.Row,
            contentPadding = PaddingValues(margin_5x),
            imageRow = contactInfoPhotoHeader(ScreenWidthClass.Big),
            socialLinks = cvData.socials
        )
        AboutSection(aboutMe = cvData.aboutMe)
    }
}

@Composable
private fun Card(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .background(DisabledColor, RoundedCornerShape_2x)
            .padding(margin_5x)
            .then(modifier)
    ) {
        content()
    }
}

@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    tabs: List<TabModel>,
    selectedTab: Tab,
    onTabSelected: (Tab) -> Unit
) {
    Column {
        Row(
            modifier = modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(margin_1x)
        ) {
            tabs.forEach { model ->
                CategoryTab(
                    tabModel = model,
                    checked = selectedTab == model.tab,
                    onSelected = onTabSelected
                )
            }
        }
    }
}

@Composable
private fun SelectedTabContent(
    modifier: Modifier = Modifier,
    selectedTab: Tab,
    cvData: CvData
) {
    ContentColumn(modifier) {
        when (selectedTab) {
            Tab.All -> {
                WorkPeriodHorizontal(workplaces = cvData.workplaces)
                LanguagesColumn(languages = cvData.languages)
                SkillsColumn(skills = cvData.skills)
                CertificatesHorizontal(certificates = cvData.certificates)
            }
            Tab.Work -> WorkPeriodHorizontal(workplaces = cvData.workplaces)
            Tab.Language -> LanguagesColumn(languages = cvData.languages)
            Tab.Skills -> SkillsColumn(skills = cvData.skills)
            Tab.Certificates -> CertificatesHorizontal(certificates = cvData.certificates)
        }
    }
}

@Composable
private fun ContentColumn(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(margin_4x),
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        content()
        ConsentFooter(Modifier.padding(top = margin_2_5x))
    }
}