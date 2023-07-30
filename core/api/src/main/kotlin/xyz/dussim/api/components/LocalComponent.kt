package xyz.dussim.api.components

import xyz.dussim.api.data.DataSource
import xyz.dussim.data.about.AboutMe
import xyz.dussim.data.certificates.Certificate
import xyz.dussim.data.languages.Language
import xyz.dussim.data.skills.Skill
import xyz.dussim.data.socials.SocialLink
import xyz.dussim.data.workplace.Workplace

public interface LocalComponent {
    public companion object;

    public val languagesDataSource: DataSource<List<Language>>
    public val aboutMeDataSource: DataSource<AboutMe>
    public val skillsDataSource: DataSource<List<Skill>>
    public val workplacesDataSource: DataSource<List<Workplace>>
    public val socialMediaDataSource: DataSource<List<SocialLink>>
    val certificatesDataSource: DataSource<List<Certificate>>
}