package xyz.dussim.cv.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import xyz.dussim.cv.data.ImList
import xyz.dussim.cv.data.LocalTextStyleProvider
import xyz.dussim.data.socials.SocialLink
import xyz.dussim.resources.R

enum class Orientation {
    Row, Column
}


@Composable
fun ContactHeader(
    modifier: Modifier = Modifier,
    socialLinksOrientation: Orientation = Orientation.Column,
    buttonsOrientation: Orientation = Orientation.Column,
    contentPadding: PaddingValues = PaddingValues(20.dp),
    socialLinks: ImList<SocialLink>,
    socials: @Composable () -> Unit = { SocialLinks(socialLinksOrientation, socialLinks) },
    buttons: @Composable () -> Unit = { DownloadAndShare(buttonsOrientation) },
    imageRow: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier.then(
            Modifier
                .background(xyz.dussim.designsystem.CardBackgroundColor, RoundedCornerShape(16.dp))
                .padding(contentPadding)
        )
    ) {
        ContactHeaderSlots(
            imageRow = imageRow,
            socials = socials,
            buttons = buttons,
            verticalArrangement = Arrangement.spacedBy(30.dp)
        )
    }
}

@Composable
fun SocialLinks(socialLinksOrientation: Orientation, socialLinks: ImList<SocialLink>) {
    when (socialLinksOrientation) {
        Orientation.Row -> SocialsRow(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
            socialLinks = socialLinks
        )

        Orientation.Column -> SocialsColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth(),
            socialLinks = socialLinks
        )
    }
}

@Composable
fun DownloadAndShare(buttonsOrientation: Orientation) {
    when (buttonsOrientation) {
        Orientation.Row -> ButtonRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Orientation.Column -> ButtonColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
private fun ContactHeaderSlots(
    imageRow: @Composable () -> Unit,
    socials: @Composable () -> Unit,
    buttons: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        imageRow()
        socials()
//        buttons()
    }
}

@Composable
private fun ButtonColumn(
    modifier: Modifier = Modifier,
    download: @Composable ColumnScope.() -> Unit = {
        DownloadButton(Modifier.fillMaxWidth()) {

        }
    },
    share: @Composable ColumnScope.() -> Unit = {
        ShareButton(Modifier.fillMaxWidth()) {

        }
    },
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        download()
        share()
    }
}

@Composable
private fun SocialsColumn(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    socialLinks: ImList<SocialLink>
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        socialLinks.forEach {
            SocialLinkRow(socialLink = it)
        }
    }
}

@Composable
private fun ButtonRow(
    modifier: Modifier = Modifier,
    download: @Composable RowScope.() -> Unit = {
        DownloadButton(Modifier.weight(1f)) {

        }
    },
    share: @Composable RowScope.() -> Unit = {
        ShareButton(Modifier.weight(1f)) {

        }
    },
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment
    ) {
        download()
        share()
    }
}

@Composable
private fun SocialsRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    socialLinks: ImList<SocialLink>
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment
    ) {
        socialLinks.forEach {
            SocialLinkRow(socialLink = it)
        }
    }
}

//TODO refactor this to Defaults, make them required param but also make overload that takes ScreenWidthClass and creates the Defaults
@Composable
fun contactInfoPhotoHeader(screenWidthClass: xyz.dussim.designsystem.ScreenWidthClass): @Composable () -> Unit {
    fun dimen(
        small: Dp, medium: Dp = small, large: Dp = medium
    ) = when (screenWidthClass) {
        xyz.dussim.designsystem.ScreenWidthClass.Small -> small
        xyz.dussim.designsystem.ScreenWidthClass.Medium -> medium
        xyz.dussim.designsystem.ScreenWidthClass.Big -> large
    }

    return {
        ContactInfoPhotoHeader(
            imageSize = dimen(56.dp, 120.dp),
            borderSize = dimen(2.dp, 4.dp),
            textSpacing = dimen(4.dp, 6.dp),
            imageTextSpacing = dimen(12.dp, 32.dp),
            textTopPadding = dimen(0.dp, 20.dp),
        )
    }
}


@Composable
fun ContactInfoPhotoHeader(
    imageSize: Dp,
    borderSize: Dp,
    textSpacing: Dp,
    imageTextSpacing: Dp,
    textTopPadding: Dp,
    borderColor: Color = xyz.dussim.designsystem.AccentColor,
    borderStroke: BorderStroke = BorderStroke(borderSize, borderColor)
) {
    val bitmap = painterResource(id = R.drawable.my_image)

    val nameStyle = LocalTextStyleProvider.current.forHeaderName()
    val jobTitleStyle = LocalTextStyleProvider.current.forHeaderJobTitle()

    Row(
        horizontalArrangement = Arrangement.spacedBy(imageTextSpacing)
    ) {
        Image(
            painter = bitmap,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(imageSize)
                .clip(CircleShape)
                .border(borderStroke, CircleShape)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(textSpacing),
            modifier = Modifier
                .padding(top = textTopPadding)
        ) {
            BasicText(text = "<Artur Tuzim/>", style = nameStyle)
            BasicText(text = "Android developer", style = jobTitleStyle)
        }
    }
}