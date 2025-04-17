package xyz.dussim.local.impl

import android.content.Intent
import xyz.dussim.api.data.DataSource
import xyz.dussim.data.socials.SocialLink
import xyz.dussim.local.R

internal class LocalSocialsDataSource : DataSource<List<SocialLink>> by LocalDataSource(STATIC_DATA) {
    companion object {
        private const val LINKEDIN_URL = "https://www.linkedin.com/in/artur-tuzim-11291017b/"

        private val STATIC_DATA =
            listOf(
                SocialLink(
                    icon = R.drawable.social_linkedin,
                    text = R.string.full_name,
                    uriString = LINKEDIN_URL,
                    action = Intent.ACTION_VIEW,
                ),
                SocialLink(
                    icon = R.drawable.social_email,
                    text = R.string.email_address,
                    uriString = "mailto:artur@tuzim.xyz",
                    action = Intent.ACTION_SENDTO,
                ),
                SocialLink(
                    icon = R.drawable.social_phone_number,
                    text = R.string.phone_number,
                    uriString = "tel:+48512389629",
                    action = Intent.ACTION_DIAL,
                ),
            )
    }
}
