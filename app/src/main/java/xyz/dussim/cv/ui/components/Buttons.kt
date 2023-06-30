package xyz.dussim.cv.ui.components

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
import xyz.dussim.cv.R
import xyz.dussim.cv.ui.components.core.CvButton
import xyz.dussim.cv.ui.components.core.CvButtonDefaults
import xyz.dussim.cv.ui.components.core.CvIcon
import xyz.dussim.cv.ui.components.core.CvOutlinedButton
import xyz.dussim.cv.ui.theme.H3

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