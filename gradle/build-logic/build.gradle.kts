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

    implementation(libs.android.gradle)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.kotlin.serialization)
    implementation(libs.kotlin.compose.compiler)
    implementation(libs.modulegraph)

    implementation(libs.ktlint.gradle)

    implementation(libs.versioning.plugin)
}

version = "1.0.0"

tasks.withType<DependencyUpdatesTask> {
    checkForGradleUpdate = true
    gradleReleaseChannel = "current"
    reportfileName = "build-logic-dependency-updates-report"
    outputDir = layout.projectDirectory.dir(".reports/versions").asFile.path

    filterConfigurations = Spec {
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
            id = name
            implementationClass = "xyz.dussim.ModuleUtilitiesPlugin"
        }
        register(conventions.plugins.xyz.dussim.ktor.app.convention) {
            id = name
            implementationClass = "xyz.dussim.KtorAppConventionPlugin"
        }
        register(conventions.plugins.xyz.dussim.multiplatform.library.convention) {
            id = name
            implementationClass = "xyz.dussim.MultiplatformLibraryConventionPlugin"
        }
        register(conventions.plugins.xyz.dussim.android.library.convention) {
            id = name
            implementationClass = "xyz.dussim.AndroidLibraryConventionPlugin"
        }
        register(conventions.plugins.xyz.dussim.android.library.compose.convention) {
            id = name
            implementationClass = "xyz.dussim.AndroidLibraryComposeConventionPlugin"
        }
        register(conventions.plugins.xyz.dussim.android.feature.convention) {
            id = name
            implementationClass = "xyz.dussim.AndroidFeatureConventionPlugin"
        }
        register(conventions.plugins.xyz.dussim.android.feature.compose.convention) {
            id = name
            implementationClass = "xyz.dussim.AndroidFeatureComposeConventionPlugin"
        }
        register(conventions.plugins.xyz.dussim.android.app.convention) {
            id = name
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

fun <T : PluginDeclaration> NamedDomainObjectContainer<T>.register(plugin: Provider<PluginDependency>, configurationAction: Action<T>) {
    register(plugin.get().pluginId, configurationAction)
}
