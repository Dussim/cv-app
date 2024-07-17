package xyz.dussim.local.impl

import xyz.dussim.api.components.LocalComponent
import xyz.dussim.api.components.MapperComponent
import xyz.dussim.api.data.DataSource
import xyz.dussim.data.about.AboutMe
import xyz.dussim.data.certificates.Certificate
import xyz.dussim.data.socials.SocialLink
import xyz.dussim.data.workplace.Workplace
import xyz.dussim.datamodel.language.Language
import xyz.dussim.datamodel.skill.Skill

private class LocalComponentImpl(
    mapperComponent: MapperComponent,
) : LocalComponent {
    override val skillsDataSource: DataSource<List<Skill>> =
        LocalSkillsDataSource(
            universalMapper = mapperComponent.universalMapper,
        )

    override val languagesDataSource: DataSource<List<Language>> =
        LocalLanguagesDataSource(
            universalMapper = mapperComponent.universalMapper,
        )

    override val aboutMeDataSource: DataSource<AboutMe> = LocalAboutMeDataSource()

    override val workplacesDataSource: DataSource<List<Workplace>> = LocalWorkplacesDataSource()

    override val socialMediaDataSource: DataSource<List<SocialLink>> = LocalSocialsDataSource()

    override val certificatesDataSource: DataSource<List<Certificate>> = LocalCertificatesDataSource()
}

fun LocalComponent.Companion.create(mapperComponent: MapperComponent): LocalComponent = LocalComponentImpl(mapperComponent)
