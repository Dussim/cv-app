package xyz.dussim.cv.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import xyz.dussim.data.about.AboutMe
import xyz.dussim.designsystem.LocalTextStyleProvider
import xyz.dussim.resources.R

@Composable
fun AboutSection(
    modifier: Modifier = Modifier,
    spacingHeight: Dp = 16.dp,
    aboutMe: AboutMe
) {
    val style = LocalTextStyleProvider.current.forSectionTitle()

    Column(modifier = modifier.fillMaxWidth()) {
        BasicText(text = stringResource(R.string.section_name_about_me), style = style)
        Spacer(modifier = Modifier.height(spacingHeight))
        BasicText(
            style = xyz.dussim.designsystem.Body1,
            text = stringResource(id = aboutMe.aboutMeRes)
        )
    }
}
