package xyz.dussim.datamodel.language.dto

import kotlinx.serialization.Serializable

@Serializable
data class LanguageDto(
    val name: LanguageName,
    val level: LanguageLevel,
    val description: String? = null
)

@Serializable
sealed interface LanguageName {

    @Serializable
    sealed interface Predefined : LanguageName {
        @Serializable
        data object English : Predefined

        @Serializable
        data object Polish : Predefined
    }

    @Serializable
    data class Custom(val name: String) : LanguageName
}

@Serializable
enum class LanguageLevel {
    A1,
    A2,
    B1,
    B2,
    C1,
    C2,
    Native
}
