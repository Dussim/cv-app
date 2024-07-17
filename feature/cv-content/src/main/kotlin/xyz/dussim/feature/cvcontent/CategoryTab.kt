package xyz.dussim.feature.cvcontent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import xyz.dussim.designsystem.H3
import xyz.dussim.designsystem.core.CVOutlinedToggleButton
import xyz.dussim.designsystem.core.CvIcon
import xyz.dussim.designsystem.margin_1x
import xyz.dussim.designsystem.margin_3x
import xyz.dussim.feature.cvcontent.model.internal.Tab
import xyz.dussim.feature.cvcontent.model.internal.TabModel

@Composable
internal fun CategoryTab(
    tabModel: TabModel,
    checked: Boolean,
    modifier: Modifier = Modifier,
    onSelected: (Tab) -> Unit,
) {
    CVOutlinedToggleButton(
        checked = checked,
        onCheckedChange = { selected ->
            if (selected) {
                onSelected(tabModel.tab)
            }
        },
        modifier = modifier,
    ) {
        if (tabModel.iconRes != null) {
            CvIcon(vectorRes = tabModel.iconRes)
            Spacer(modifier = Modifier.width(margin_1x))
        }
        Box(modifier = Modifier.heightIn(min = margin_3x)) {
            BasicText(
                text = stringResource(id = tabModel.textRes),
                style = H3,
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}
