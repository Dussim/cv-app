import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions

plugins {
    `kotlin-dsl`
    alias(libs.plugins.gradle.versions)
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(files(ktor.javaClass.superclass.protectionDomain.codeSource.location))

    implementation(
        libs.plugins.android.library
            .toMavenCoordinates(),
    )
    implementation(
        libs.plugins.android.application
            .toMavenCoordinates(),
    )
    implementation(
        libs.plugins.kotlin.android
            .toMavenCoordinates(),
    )
    implementation(
        libs.plugins.kotlin.serialization
            .toMavenCoordinates(),
    )
    implementation(
        libs.plugins.kotlin.compose
            .toMavenCoordinates(),
    )
    implementation(
        libs.plugins.modulegraph
            .toMavenCoordinates(),
    )
    implementation(
        libs.plugins.ktlint
            .toMavenCoordinates(),
    )
    implementation(libs.versioning.plugin)
}

version = "1.0.0"

tasks.withType<DependencyUpdatesTask> {
    checkForGradleUpdate = true
    gradleReleaseChannel = "current"
    reportfileName = "build-logic-dependency-updates-report"
    outputDir =
        layout.projectDirectory
            .dir(".reports/versions")
            .asFile.path

    filterConfigurations =
        Spec {
            !it.isCanBeConsumed
        }

    rejectVersionIf {
        candidate.version.isNonStable() && !currentVersion.isNonStable()
    }

    outputFormatter = "html,json"
}

fun String.isNonStable(): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(this)
    return !isStable
}

gradlePlugin {
    plugins {
        register(conventions.plugins.xyz.dussim.module.utilities) {
            implementationClass = "xyz.dussim.ModuleUtilitiesPlugin"
        }
        register(conventions.plugins.xyz.dussim.ktor.app.convention) {
            implementationClass = "xyz.dussim.KtorAppConventionPlugin"
        }
        register(conventions.plugins.xyz.dussim.multiplatform.library.convention) {
            implementationClass = "xyz.dussim.MultiplatformLibraryConventionPlugin"
        }
        register(conventions.plugins.xyz.dussim.android.library.convention) {
            implementationClass = "xyz.dussim.AndroidLibraryConventionPlugin"
        }
        register(conventions.plugins.xyz.dussim.android.library.compose.convention) {
            implementationClass = "xyz.dussim.AndroidLibraryComposeConventionPlugin"
        }
        register(conventions.plugins.xyz.dussim.android.feature.convention) {
            implementationClass = "xyz.dussim.AndroidFeatureConventionPlugin"
        }
        register(conventions.plugins.xyz.dussim.android.feature.compose.convention) {
            implementationClass = "xyz.dussim.AndroidFeatureComposeConventionPlugin"
        }
        register(conventions.plugins.xyz.dussim.android.app.convention) {
            implementationClass = "xyz.dussim.AndroidAppConventionPlugin"
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin.target.compilations.configureEach {
    compileTaskProvider.configure {
        compilerOptions {
            (this as KotlinJvmCompilerOptions).jvmTarget.set(JvmTarget.JVM_17)
            freeCompilerArgs.add("-Xjdk-release=17")
        }
    }
}

/**
 * Converts a Gradle plugin dependency to its Maven coordinate notation.
 *
 * This extension function transforms a plugin dependency into the format required
 * by Gradle's classpath dependencies: `{pluginId}:{pluginId}.gradle.plugin:{version}`
 *
 * @receiver Provider of the plugin dependency to convert
 * @return Provider of the Maven coordinate string
 *
 * @sample
 * ```
 * libs.plugins.android.library.toMavenCoordinates()
 * // Returns: "com.android.library:com.android.library.gradle.plugin:8.12.3"
 * ```
 */
fun Provider<PluginDependency>.toMavenCoordinates(): Provider<String> =
    map {
        "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}"
    }

fun <T : PluginDeclaration> NamedDomainObjectContainer<T>.register(
    plugin: Provider<PluginDependency>,
    configurationAction: Action<T>,
) {
    register(plugin.get().pluginId) {
        id = name
        configurationAction.execute(this)
    }
}
