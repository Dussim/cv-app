package xyz.dussim.data.languages

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

//import androidx.compose.runtime.Immutable
//import xyz.dussim.resources.R

//@Immutable
@Parcelize
public data class Language(
    @StringRes val languageRes: Int,
    val level: Level
) : Parcelable {
    public sealed interface Level : Parcelable

    public interface Translatable : Level {
        @get:StringRes
        public val resId: Int
    }

    public interface NonTranslatable : Level {
        public val name: String
    }
}