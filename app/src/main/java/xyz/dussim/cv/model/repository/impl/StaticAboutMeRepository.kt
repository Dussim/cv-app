package xyz.dussim.cv.model.repository.impl

import xyz.dussim.cv.R
import xyz.dussim.cv.model.external.about.AboutMe
import xyz.dussim.cv.model.repository.AboutMeRepository

internal object StaticAboutMeRepository : AboutMeRepository {
    private val STATIC_DATA = AboutMe(R.string.about_me)

    override suspend fun fetchAboutMe() = STATIC_DATA
}