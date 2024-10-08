package xyz.dussim.api.components

import xyz.dussim.api.data.DataSource
import xyz.dussim.data.about.AboutMe
import xyz.dussim.data.certificates.Certificate
import xyz.dussim.data.socials.SocialLink
import xyz.dussim.data.workplace.Workplace
import xyz.dussim.datamodel.language.Language
import xyz.dussim.datamodel.skill.Skill

interface LocalComponent {
    companion object;

    val languagesDataSource: DataSource<List<Language>>
    val aboutMeDataSource: DataSource<AboutMe>
    val skillsDataSource: DataSource<List<Skill>>
    val workplacesDataSource: DataSource<List<Workplace>>
    val socialMediaDataSource: DataSource<List<SocialLink>>
    val certificatesDataSource: DataSource<List<Certificate>>
}
