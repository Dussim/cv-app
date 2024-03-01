package xyz.dussim.datamodel.language

import android.content.Context
import xyz.dussim.datamodel.language.dto.LanguageDto
import xyz.dussim.datamodel.language.dto.toDisplayString

fun interface LanguageMapper {
    companion object {
        operator fun invoke(context: Context) = LanguageMapper { languageDto ->
            Language(
                name = languageDto.name.toDisplayString(context),
                level = languageDto.level.toDisplayString(context)
            )
        }
    }

    fun map(languageDto: LanguageDto): Language

    fun mapLanguages(languageDtos: List<LanguageDto>): List<Language> = languageDtos.map(::map)
}
