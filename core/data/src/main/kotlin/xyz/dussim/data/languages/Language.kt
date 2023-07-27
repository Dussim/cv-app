package xyz.dussim.data.languages

import androidx.annotation.StringRes

//import androidx.compose.runtime.Immutable
//import xyz.dussim.resources.R

//@Immutable
public data class Language(
    @StringRes val languageRes: Int,
    val level: Level
) {
    public sealed interface Level

    public interface Translatable : Level {
        @get:StringRes
        public val resId: Int
    }

    public interface NonTranslatable : Level {
        public val name: String
    }
}