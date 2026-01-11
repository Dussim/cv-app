package xyz.dussim.data.projects

import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class Project(
    @StringRes val name: Int,
    @StringRes val description: Int,
    @StringRes val githubUrl: Int,
    @StringRes val contentDescription: Int,
) : Parcelable
