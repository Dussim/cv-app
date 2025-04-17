package xyz.dussim.model.impl

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import xyz.dussim.data.CvData
import xyz.dussim.data.about.AboutMe
import xyz.dussim.data.certificates.Certificate
import xyz.dussim.data.projects.Project
import xyz.dussim.data.socials.SocialLink
import xyz.dussim.data.workplace.Workplace
import xyz.dussim.datamodel.language.Language
import xyz.dussim.datamodel.skill.Skill
import xyz.dussim.model.impl.fakes.FakeDataSource
import java.time.YearMonth

class CvDataSourceTest : FunSpec({

    val testDispatcher = StandardTestDispatcher()

    beforeTest {
        Dispatchers.setMain(testDispatcher)
    }

    context("CvDataSource") {
        test("should fetch data from all sources and combine them into CvData") {
            // Arrange
            val skills =
                listOf(
                    Skill("Kotlin", 0.9f, "Kotlin programming language"),
                    Skill("Android", 0.85f, "Android development"),
                )

            val languages =
                listOf(
                    Language("English", "C2"),
                    Language("Spanish", "B1"),
                )

            val aboutMe = AboutMe(123)

            val workplaces =
                listOf(
                    Workplace(
                        startDate = YearMonth.of(2020, 1),
                        endDate = YearMonth.of(2022, 12),
                        workTitle = 456,
                        location = 789,
                        description = 101,
                    ),
                    Workplace(
                        startDate = YearMonth.of(2023, 1),
                        endDate = null,
                        workTitle = 112,
                        location = 131,
                        description = 415,
                    ),
                )

            val socials =
                listOf(
                    SocialLink(
                        icon = 161,
                        text = 718,
                        uriString = "https://example.com",
                        action = "Open website",
                    ),
                )

            val certificates =
                listOf(
                    Certificate(
                        name = 192,
                        date = YearMonth.of(2021, 6),
                        link = "https://cert.example.com",
                    ),
                )

            val projects =
                listOf(
                    Project(
                        name = 202,
                        description = 212,
                        githubUrl = 222,
                        contentDescription = 232,
                    ),
                )

            val skillsDataSource = FakeDataSource(skills)
            val languagesDataSource = FakeDataSource(languages)
            val aboutMeDataSource = FakeDataSource(aboutMe)
            val workplacesDataSource = FakeDataSource(workplaces)
            val socialsDataSource = FakeDataSource(socials)
            val certificatesDataSource = FakeDataSource(certificates)
            val projectsDataSource = FakeDataSource(projects)

            val cvDataSource =
                CvDataSource(
                    skillsDataSource = skillsDataSource,
                    languagesDataSource = languagesDataSource,
                    aboutMeDataSource = aboutMeDataSource,
                    workplacesDataSource = workplacesDataSource,
                    socialsDataSource = socialsDataSource,
                    certificatesDataSource = certificatesDataSource,
                    projectsDataSource = projectsDataSource,
                    dispatcher = testDispatcher,
                )

            // Act
            runTest {
                val result = cvDataSource.fetch()

                // Assert
                result shouldBe
                    CvData(
                        skills = skills,
                        languages = languages,
                        aboutMe = aboutMe,
                        workplaces = workplaces,
                        socials = socials,
                        certificates = certificates,
                        projects = projects,
                    )
            }
        }
    }
})
