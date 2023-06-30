package xyz.dussim.cv.ui.utils

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import xyz.dussim.cv.ui.theme.BackgroundColorLong
import java.lang.annotation.Inherited


@Inherited
@PreviewPhone
@PreviewTablet
annotation class PreviewPhoneTablet


@Preview(
    name = "PHONE",
    device = Devices.PHONE,
    widthDp = 240,
    showBackground = true,
    backgroundColor = BackgroundColorLong
)
@Inherited
annotation class PreviewPhone

@Preview(
    name = "TABLET",
    device = Devices.TABLET,
    widthDp = 240,
    showBackground = true,
    backgroundColor = BackgroundColorLong
)
@Inherited
annotation class PreviewTablet

@Preview(
    name = "TABLET",
    device = Devices.TABLET,
    widthDp = 768,
    showBackground = true,
    backgroundColor = BackgroundColorLong
)
@Inherited
annotation class PreviewTabletWide

@Preview(
    name = "TABLET",
    device = Devices.DESKTOP,
    widthDp = 1920,
    showBackground = true,
    backgroundColor = BackgroundColorLong
)
@Inherited
annotation class PreviewDesktop