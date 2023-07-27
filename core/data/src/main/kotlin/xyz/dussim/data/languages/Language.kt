package xyz.dussim.data.languages

import androidx.annotation.StringRes
import xyz.dussim.resources.R

//import androidx.compose.runtime.Immutable
//import xyz.dussim.resources.R

//@Immutable
public data class Language(
    @StringRes val languageRes: Int,
    val level: Level
) {
    public sealed interface Level

    public sealed interface Translatable : Level {
        @get:StringRes
        public val resId: Int
    }

    public sealed interface NonTranslatable : Level {
        public val name: String
    }

    public data object B2 : NonTranslatable {
        override val name: String = "B2"
    }

    public data object C1 : NonTranslatable {
        override val name: String = "C1"
    }

    public data object Native : Translatable {
        @StringRes
        override val resId: Int = R.string.language_level_native
    }
}