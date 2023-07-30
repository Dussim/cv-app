package xyz.dussim.feature.cvcontent

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import xyz.dussim.data.CvData
import xyz.dussim.designsystem.ScreenWidthClass
import xyz.dussim.designsystem.core.HidingHeader
import xyz.dussim.designsystem.margin_1x
import xyz.dussim.designsystem.margin_2_5x
import xyz.dussim.designsystem.margin_4x
import xyz.dussim.feature.cvcontent.components.*
import xyz.dussim.feature.cvcontent.model.internal.Tab
import xyz.dussim.feature.cvcontent.model.internal.TabModel

@Composable
internal fun SmallLayout(cvData: CvData, screenModel: CvContentScreenModel) {
    val tabs by screenModel.tabs.collectAsState()
    val selectedTab by screenModel.selectedTab.collectAsState()

    HidingHeader(
        contentPadding = PaddingValues(margin_1x),
        header = { HeaderColumn(cvData = cvData) }
    ) { scrollState ->

        Column(
            modifier = Modifier.padding(top = margin_4x)
        ) {
            TopBar(
                tabs = tabs,
                selectedTab = selectedTab,
                onTabSelected = screenModel::onTabSelected
            )
            Spacer(modifier = Modifier.height(margin_4x))
            SelectedTabContent(
                modifier = Modifier.verticalScroll(scrollState),
                selectedTab = selectedTab,
                cvData = cvData
            )
        }
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
private fun HeaderColumn(
    modifier: Modifier = Modifier,
    cvData: CvData
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.spacedBy(margin_4x)
    ) {
        ContactHeader(
            socialLinksOrientation = Orientation.Column,
            buttonsOrientation = Orientation.Column,
            contentPadding = PaddingValues(margin_2_5x),
            imageRow = contactInfoPhotoHeader(ScreenWidthClass.Small),
            socialLinks = cvData.socials
        )
        AboutSection(aboutMe = cvData.aboutMe)
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
                WorkPeriodVertical(workplaces = cvData.workplaces)
                LanguagesColumn(languages = cvData.languages)
                SkillsColumn(skills = cvData.skills)
                CertificatesVertical(certificates = cvData.certificates)
            }
            Tab.Work -> WorkPeriodVertical(workplaces = cvData.workplaces)
            Tab.Language -> LanguagesColumn(languages = cvData.languages)
            Tab.Skills -> SkillsColumn(skills = cvData.skills)
            Tab.Certificates -> CertificatesVertical(certificates = cvData.certificates)
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