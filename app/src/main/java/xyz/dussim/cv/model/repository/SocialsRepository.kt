package xyz.dussim.cv.model.repository

import xyz.dussim.cv.model.external.socials.SocialLink

fun interface SocialsRepository {
    suspend fun fetchSocials(): List<SocialLink>
}