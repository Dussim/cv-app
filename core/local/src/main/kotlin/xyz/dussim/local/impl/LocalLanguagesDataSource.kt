package xyz.dussim.local.impl

import androidx.annotation.StringRes
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import xyz.dussim.api.data.DataSource
import xyz.dussim.data.languages.Language
import xyz.dussim.local.R

internal class LocalLanguagesDataSource : DataSource<List<Language>> by LocalDataSource(STATIC_DATA) {
    companion object {
        @Parcelize
        data object B2 : Language.NonTranslatable {
            @IgnoredOnParcel
            override val name: String = "B2"
        }

        @Parcelize
        data object Native : Language.Translatable {
            @StringRes
            @IgnoredOnParcel
            override val resId: Int = R.string.language_level_native
        }

        private val STATIC_DATA = listOf(
            Language(R.string.language_polish, Native),
            Language(R.string.language_english, B2)
        )
    }
}