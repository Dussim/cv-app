package xyz.dussim.local.impl

import xyz.dussim.api.data.DataSource
import xyz.dussim.data.about.AboutMe
import xyz.dussim.local.R

internal class LocalAboutMeDataSource : DataSource<AboutMe> by LocalDataSource(STATIC_DATA) {
    companion object {
        private val STATIC_DATA = AboutMe(R.string.about_me)
    }
}
