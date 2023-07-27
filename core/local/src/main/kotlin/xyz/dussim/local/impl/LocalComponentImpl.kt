package xyz.dussim.local.impl

import xyz.dussim.api.components.LocalComponent
import xyz.dussim.api.data.DataSource
import xyz.dussim.data.about.AboutMe
import xyz.dussim.data.languages.Language
import xyz.dussim.data.skills.Skill
import xyz.dussim.data.socials.SocialLink
import xyz.dussim.data.workplace.Workplace

internal class LocalComponentImpl(
    override val skillsDataSource: DataSource<List<Skill>> = LocalSkillsDataSource(),
    override val languagesDataSource: DataSource<List<Language>> = LocalLanguagesDataSource(),
    override val aboutMeDataSource: DataSource<AboutMe> = LocalAboutMeDataSource(),
    override val workplacesDataSource: DataSource<List<Workplace>> = LocalWorkplacesDataSource(),
    override val socialMediaDataSource: DataSource<List<SocialLink>> = LocalSocialsDataSource()
) : LocalComponent

public fun LocalComponent.Companion.create(): LocalComponent = LocalComponentImpl()