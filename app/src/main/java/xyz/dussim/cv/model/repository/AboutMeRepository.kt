package xyz.dussim.cv.model.repository

import xyz.dussim.cv.model.external.about.AboutMe

fun interface AboutMeRepository {
    suspend fun fetchAboutMe(): AboutMe
}