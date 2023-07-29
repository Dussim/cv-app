package xyz.dussim.feature.cvcontent.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import xyz.dussim.designsystem.H3
import xyz.dussim.designsystem.core.CvButton
import xyz.dussim.designsystem.core.CvButtonDefaults
import xyz.dussim.designsystem.core.CvIcon
import xyz.dussim.designsystem.core.CvOutlinedButton
import xyz.dussim.resources.R

@Composable
fun DownloadButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val icon = rememberVectorPainter(
        ImageVector.vectorResource(R.drawable.download)
    )

    val text = stringResource(R.string.button_download_text)

    CvButton(
        onClick = onClick,
        modifier = modifier
    ) {
        BasicText(text, style = H3)
        Spacer(modifier = Modifier.width(10.dp))
        CvIcon(icon, contentDescription = text)
    }
}

@Composable
fun ShareButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val icon = rememberVectorPainter(
        ImageVector.vectorResource(R.drawable.share)
    )

    val text = stringResource(R.string.button_share_text)

    CvOutlinedButton(
        onClick = onClick,
        modifier = modifier,
        shape = CvButtonDefaults.Shape,
        contentPadding = CvButtonDefaults.ContentPadding
    ) {
        BasicText(text, style = H3)
        Spacer(modifier = Modifier.width(10.dp))
        CvIcon(icon, contentDescription = text)
    }
}