package xyz.dussim.local.impl

import xyz.dussim.api.data.DataSource
import xyz.dussim.data.languages.Language
import xyz.dussim.resources.R

internal class LocalLanguagesDataSource : DataSource<List<Language>> by LocalDataSource(STATIC_DATA) {
    companion object {
        private val STATIC_DATA = listOf(
            Language(R.string.language_polish, Language.Native),
            Language(R.string.language_english, Language.B2)
        )
    }
}