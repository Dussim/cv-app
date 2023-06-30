package xyz.dussim.cv.model.repository.impl

import android.content.Intent
import android.net.Uri
import xyz.dussim.cv.R
import xyz.dussim.cv.model.external.socials.SocialLink
import xyz.dussim.cv.model.repository.SocialsRepository

internal object StaticSocialsRepository : SocialsRepository {
    private const val LinkedinUrl = "https://www.linkedin.com/in/artur-tuzim-11291017b/"

    private val STATIC_DATA = listOf(
        SocialLink(
            icon = R.drawable.social_linkedin,
            text = R.string.full_name,
            uri = Uri.parse(LinkedinUrl),
            action = Intent.ACTION_VIEW
        ),
        SocialLink(
            icon = R.drawable.social_email,
            text = R.string.email_address,
            uri = Uri.parse("mailto:artur@tuzim.xyz"),
            action = Intent.ACTION_SENDTO
        ),//fixme fix uri
        SocialLink(
            icon = R.drawable.social_phone_number,
            text = R.string.phone_number,
            uri = Uri.parse("tel:+48512389629"),
            action = Intent.ACTION_DIAL
        )//fixme fix uri
    )

    override suspend fun fetchSocials() = STATIC_DATA
}