package xyz.dussim.cv.model.repository.impl

import xyz.dussim.cv.R
import xyz.dussim.cv.model.external.languages.Language
import xyz.dussim.cv.model.repository.LanguagesRepository

internal object StaticLanguagesRepository : LanguagesRepository {
    private val STATIC_DATA = listOf(
        Language(R.string.language_polish, Language.Native),
        Language(R.string.language_english, Language.B2)
    )

    override suspend fun fetchLanguages() = STATIC_DATA
}