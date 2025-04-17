package xyz.dussim.model.impl

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import xyz.dussim.datamodel.language.Language
import xyz.dussim.model.impl.fakes.FakeFailureNetworkDataSource
import xyz.dussim.model.impl.fakes.FakeLocalDataSource
import xyz.dussim.model.impl.fakes.FakeSuccessNetworkDataSource
import xyz.dussim.model.impl.fakes.FakeTimeoutNetworkDataSource

class NetworkFirstLanguagesDataSourceTest : FunSpec({

    context("NetworkFirstLanguagesDataSource") {
        test("should return network data when network fetch succeeds") {
            // Arrange
            val networkLanguages =
                listOf(
                    Language("English", "en"),
                    Language("Spanish", "es"),
                )
            val localLanguages =
                listOf(
                    Language("French", "fr"),
                    Language("German", "de"),
                )

            val networkDataSource = FakeSuccessNetworkDataSource(networkLanguages)
            val localDataSource = FakeLocalDataSource(localLanguages)

            val dataSource =
                NetworkFirstLanguagesDataSource(
                    network = networkDataSource,
                    local = localDataSource,
                )

            // Act
            runTest {
                val result = dataSource.fetch()

                // Assert
                result shouldBe networkLanguages
            }
        }

        test("should return local data when network fetch fails") {
            // Arrange
            val localLanguages =
                listOf(
                    Language("French", "fr"),
                    Language("German", "de"),
                )

            val networkDataSource = FakeFailureNetworkDataSource<List<Language>>(RuntimeException("Network error"))
            val localDataSource = FakeLocalDataSource(localLanguages)

            val dataSource =
                NetworkFirstLanguagesDataSource(
                    network = networkDataSource,
                    local = localDataSource,
                )

            // Act
            runTest {
                val result = dataSource.fetch()

                // Assert
                result shouldBe localLanguages
            }
        }

        test("should return local data when network fetch times out") {
            // Arrange
            val localLanguages =
                listOf(
                    Language("French", "fr"),
                    Language("German", "de"),
                )

            val networkDataSource = FakeTimeoutNetworkDataSource<List<Language>>()
            val localDataSource = FakeLocalDataSource(localLanguages)

            val dataSource =
                NetworkFirstLanguagesDataSource(
                    network = networkDataSource,
                    local = localDataSource,
                )

            // Act
            runTest {
                val result = dataSource.fetch()

                // Assert
                result shouldBe localLanguages
            }
        }
    }
})
