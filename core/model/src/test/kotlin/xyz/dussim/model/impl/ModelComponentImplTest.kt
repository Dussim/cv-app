package xyz.dussim.model.impl

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import xyz.dussim.api.components.ModelComponent
import xyz.dussim.data.CvData
import xyz.dussim.data.about.AboutMe
import xyz.dussim.data.certificates.Certificate
import xyz.dussim.data.projects.Project
import xyz.dussim.data.socials.SocialLink
import xyz.dussim.data.workplace.Workplace
import xyz.dussim.datamodel.language.Language
import xyz.dussim.datamodel.skill.Skill
import xyz.dussim.model.impl.fakes.FakeDispatchersComponent
import xyz.dussim.model.impl.fakes.FakeLocalComponent
import xyz.dussim.model.impl.fakes.FakeNetworkComponent
import java.time.YearMonth

class ModelComponentImplTest :
    FunSpec({

        val testDispatcher = StandardTestDispatcher()

        context("ModelComponentImpl") {
            test("should create CvDataSource with correct dependencies") {
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

                val dispatchersComponent = FakeDispatchersComponent(testDispatcher)
                val localComponent =
                    FakeLocalComponent(
                        skills = skills,
                        languages = languages,
                        aboutMe = aboutMe,
                        workplaces = workplaces,
                        socials = socials,
                        certificates = certificates,
                        projects = projects,
                    )
                val networkComponent =
                    FakeNetworkComponent(
                        skills = skills,
                        languages = languages,
                    )

                // Act
                val modelComponent =
                    ModelComponent.create(
                        dispatchersComponent = dispatchersComponent,
                        localComponent = localComponent,
                        networkComponent = networkComponent,
                    )

                // Assert
                modelComponent.shouldBeInstanceOf<ModelComponentImpl>()

                // Verify that the CvDataSource is created correctly
                runTest {
                    val result = modelComponent.cvDataSource.fetch()

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
