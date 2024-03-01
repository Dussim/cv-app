package xyz.dussim.datamodel.language.dto

import android.content.Context
import xyz.dussim.datamodel.R
import xyz.dussim.datamodel.language.dto.LanguageLevel.A1
import xyz.dussim.datamodel.language.dto.LanguageLevel.A2
import xyz.dussim.datamodel.language.dto.LanguageLevel.B1
import xyz.dussim.datamodel.language.dto.LanguageLevel.B2
import xyz.dussim.datamodel.language.dto.LanguageLevel.C1
import xyz.dussim.datamodel.language.dto.LanguageLevel.C2
import xyz.dussim.datamodel.language.dto.LanguageLevel.Native
import xyz.dussim.datamodel.language.dto.LanguageName.Custom
import xyz.dussim.datamodel.language.dto.LanguageName.Predefined.English
import xyz.dussim.datamodel.language.dto.LanguageName.Predefined.Polish

fun LanguageLevel.toDisplayString(context: Context): String = when (this) {
    A1, A2, B1, B2, C1, C2 -> name
    Native -> context.getString(R.string.data_model_language_level_native)
}

fun LanguageName.toDisplayString(context: Context): String = when (this) {
    Polish -> context.getString(R.string.data_model_language_name_polish)
    English -> context.getString(R.string.data_model_language_name_english)
    is Custom -> this.name
}
