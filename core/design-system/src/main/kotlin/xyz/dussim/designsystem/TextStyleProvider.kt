package xyz.dussim.designsystem

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

// TODO rename all styles, this module doesnt know what skills or languages etc. are
interface TextStyleProvider {
    object Default : TextStyleProvider

    object Big : TextStyleProvider {
        override fun forHeaderName(): TextStyle = H1

        override fun forSectionTitle(): TextStyle = H1

        override fun forWorkTitle(): TextStyle = H2

        override fun forLanguageChip(): TextStyle = H3

        override fun forSkills(): TextStyle = H3

        override fun forCertificate(): TextStyle = H3
    }

    fun forHeaderName(): TextStyle = H2

    fun forHeaderJobTitle(): TextStyle = Body1.copy(TextAlternative)

    fun forSectionTitle(): TextStyle = H2

    fun forWorkTitle(): TextStyle = H3

    fun forLanguageChip(): TextStyle = Caption

    fun forSkills(): TextStyle = Caption

    fun forCertificate(): TextStyle = Caption
}

val LocalTextStyleProvider: ProvidableCompositionLocal<TextStyleProvider> =
    staticCompositionLocalOf {
        TextStyleProvider.Default
    }
