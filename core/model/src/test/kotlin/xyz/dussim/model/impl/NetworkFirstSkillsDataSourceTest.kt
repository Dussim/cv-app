package xyz.dussim.model.impl

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import xyz.dussim.datamodel.skill.Skill
import xyz.dussim.model.impl.fakes.FakeFailureNetworkDataSource
import xyz.dussim.model.impl.fakes.FakeLocalDataSource
import xyz.dussim.model.impl.fakes.FakeSuccessNetworkDataSource
import xyz.dussim.model.impl.fakes.FakeTimeoutNetworkDataSource

class NetworkFirstSkillsDataSourceTest : FunSpec({

    context("NetworkFirstSkillsDataSource") {
        test("should return network data when network fetch succeeds") {
            // Arrange
            val networkSkills =
                listOf(
                    Skill("Kotlin", 0.9f, "Kotlin programming language"),
                    Skill("Android", 0.85f, "Android development"),
                )
            val localSkills =
                listOf(
                    Skill("Java", 0.8f, "Java programming language"),
                    Skill("Spring", 0.7f, "Spring framework"),
                )

            val networkDataSource = FakeSuccessNetworkDataSource(networkSkills)
            val localDataSource = FakeLocalDataSource(localSkills)

            val dataSource =
                NetworkFirstSkillsDataSource(
                    network = networkDataSource,
                    local = localDataSource,
                )

            // Act
            runTest {
                val result = dataSource.fetch()

                // Assert
                result shouldBe networkSkills
            }
        }

        test("should return local data when network fetch fails") {
            // Arrange
            val localSkills =
                listOf(
                    Skill("Java", 0.8f, "Java programming language"),
                    Skill("Spring", 0.7f, "Spring framework"),
                )

            val networkDataSource = FakeFailureNetworkDataSource<List<Skill>>(RuntimeException("Network error"))
            val localDataSource = FakeLocalDataSource(localSkills)

            val dataSource =
                NetworkFirstSkillsDataSource(
                    network = networkDataSource,
                    local = localDataSource,
                )

            // Act
            runTest {
                val result = dataSource.fetch()

                // Assert
                result shouldBe localSkills
            }
        }

        test("should return local data when network fetch times out") {
            // Arrange
            val localSkills =
                listOf(
                    Skill("Java", 0.8f, "Java programming language"),
                    Skill("Spring", 0.7f, "Spring framework"),
                )

            val networkDataSource = FakeTimeoutNetworkDataSource<List<Skill>>()
            val localDataSource = FakeLocalDataSource(localSkills)

            val dataSource =
                NetworkFirstSkillsDataSource(
                    network = networkDataSource,
                    local = localDataSource,
                )

            // Act
            runTest {
                val result = dataSource.fetch()

                // Assert
                result shouldBe localSkills
            }
        }
    }
})
