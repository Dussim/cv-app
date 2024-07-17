package xyz.dussim.feature.cvcontent

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import xyz.dussim.data.CvData
import xyz.dussim.designsystem.DisabledColor
import xyz.dussim.designsystem.RoundedCornerShape_2x
import xyz.dussim.designsystem.ScreenWidthClass
import xyz.dussim.designsystem.core.HidingHeader
import xyz.dussim.designsystem.margin_1x
import xyz.dussim.designsystem.margin_2_5x
import xyz.dussim.designsystem.margin_4x
import xyz.dussim.feature.cvcontent.components.AboutSection
import xyz.dussim.feature.cvcontent.components.CertificatesHorizontal
import xyz.dussim.feature.cvcontent.components.ConsentFooter
import xyz.dussim.feature.cvcontent.components.ContactHeader
import xyz.dussim.feature.cvcontent.components.LanguagesColumn
import xyz.dussim.feature.cvcontent.components.Orientation
import xyz.dussim.feature.cvcontent.components.SkillsColumn
import xyz.dussim.feature.cvcontent.components.WorkPeriodHorizontal
import xyz.dussim.feature.cvcontent.components.contactInfoPhotoHeader
import xyz.dussim.feature.cvcontent.model.internal.Tab
import xyz.dussim.feature.cvcontent.model.internal.TabModel

@Composable
internal fun MediumLayout(
    cvData: CvData,
    screenModel: CvContentScreenModel,
) {
    val tabs by screenModel.tabs.collectAsState()
    val selectedTab by screenModel.selectedTab.collectAsState()

    HidingHeader(
        contentPadding = PaddingValues(margin_1x),
        header = { HeaderColumn(cvData = cvData) },
    ) { scrollState ->

        Column(
            modifier =
                Modifier
                    .padding(top = margin_4x)
                    .background(DisabledColor, RoundedCornerShape_2x)
                    .padding(margin_2_5x),
        ) {
            TopBar(
                tabs = tabs,
                selectedTab = selectedTab,
                onTabSelected = screenModel::onTabSelected,
            )
            Spacer(modifier = Modifier.height(margin_4x))
            SelectedTabContent(
                modifier = Modifier.verticalScroll(scrollState),
                selectedTab = selectedTab,
                cvData = cvData,
            )
        }
    }
}

@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    tabs: List<TabModel>,
    selectedTab: Tab,
    onTabSelected: (Tab) -> Unit,
) {
    Column {
        Row(
            modifier = modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(margin_1x),
        ) {
            tabs.forEach { model ->
                CategoryTab(
                    tabModel = model,
                    checked = selectedTab == model.tab,
                    onSelected = onTabSelected,
                )
            }
        }
    }
}

@Composable
private fun HeaderColumn(
    modifier: Modifier = Modifier,
    cvData: CvData,
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.spacedBy(margin_4x),
    ) {
        ContactHeader(
            socialLinksOrientation = Orientation.Row,
            buttonsOrientation = Orientation.Row,
            contentPadding = PaddingValues(margin_2_5x),
            imageRow = contactInfoPhotoHeader(ScreenWidthClass.Medium),
            socialLinks = cvData.socials,
        )
        AboutSection(aboutMe = cvData.aboutMe)
    }
}

@Composable
private fun SelectedTabContent(
    modifier: Modifier = Modifier,
    selectedTab: Tab,
    cvData: CvData,
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
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
    ) {
        content()
        ConsentFooter(Modifier.padding(top = margin_2_5x))
    }
}
