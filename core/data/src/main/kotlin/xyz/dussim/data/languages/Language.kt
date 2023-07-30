package xyz.dussim.data.languages

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Language(
    @StringRes val languageRes: Int,
    val level: Level
) : Parcelable {
    sealed interface Level : Parcelable

    interface Translatable : Level {
        @get:StringRes
        val resId: Int
    }

    interface NonTranslatable : Level {
        val name: String
    }
}