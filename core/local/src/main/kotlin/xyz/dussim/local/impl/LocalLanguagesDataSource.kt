package xyz.dussim.local.impl

import xyz.dussim.api.data.DataSource
import xyz.dussim.datamodel.UniversalMapper
import xyz.dussim.datamodel.language.Language
import xyz.dussim.datamodel.language.dto.LanguageDto
import xyz.dussim.datamodel.language.dto.LanguageLevel
import xyz.dussim.datamodel.language.dto.LanguageName.Predefined

internal class LocalLanguagesDataSource(
    universalMapper: UniversalMapper
) : DataSource<List<Language>> by LocalDataSource(staticData(universalMapper)) {
    companion object {
        private fun staticData(universalMapper: UniversalMapper) = listOf(
            LanguageDto(Predefined.Polish, LanguageLevel.Native),
            LanguageDto(Predefined.English, LanguageLevel.C1)
        ).map(universalMapper::map)
    }
}
