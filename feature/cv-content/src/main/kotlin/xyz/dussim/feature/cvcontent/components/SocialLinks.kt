package xyz.dussim.feature.cvcontent.components

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import xyz.dussim.data.socials.SocialLink
import xyz.dussim.designsystem.H3
import xyz.dussim.designsystem.core.CvIcon

private val H3UnderlineSpanStyle: SpanStyle
    @Composable
    get() = H3.copy(textDecoration = TextDecoration.Underline).toSpanStyle()

private val HorizontalArrangement
    @Composable get() = Arrangement.spacedBy(10.dp)

@Composable
internal fun SocialLinkRow(socialLink: SocialLink) {
    val context = LocalContext.current

    val text = stringResource(id = socialLink.text)

    val annotatedString =
        buildAnnotatedString {
            append(text)
            addStyle(H3UnderlineSpanStyle, 0, text.length)
        }

    Row(
        horizontalArrangement = HorizontalArrangement,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CvIcon(vectorRes = socialLink.icon)
        ClickableText(annotatedString) {
            val intent = Intent(socialLink.action, socialLink.uri)

            context.startActivity(intent)
        }
    }
}
