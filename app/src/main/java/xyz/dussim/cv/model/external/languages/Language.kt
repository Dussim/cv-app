package xyz.dussim.cv.model.external.languages

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import xyz.dussim.cv.R

@Immutable
data class Language(
    @StringRes val languageRes: Int,
    val level: Level
) {
    sealed interface Level

    sealed interface Translatable : Level {
        @get:StringRes
        val resId: Int
    }

    sealed interface NonTranslatable : Level {
        val name: String
    }

    object B2 : NonTranslatable {
        override val name: String = "B2"
    }

    object C1 : NonTranslatable {
        override val name: String = "C1"
    }

    object Native : Translatable {
        @StringRes
        override val resId: Int = R.string.language_level_native
    }
}