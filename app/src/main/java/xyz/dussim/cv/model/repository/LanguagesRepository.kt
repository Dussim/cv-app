package xyz.dussim.cv.model.repository

import xyz.dussim.cv.model.external.languages.Language

fun interface LanguagesRepository {
    suspend fun fetchLanguages(): List<Language>
}