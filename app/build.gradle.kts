plugins {
    alias(conventions.plugins.xyz.dussim.android.app.convention)
    alias(conventions.plugins.xyz.dussim.build.parameters)
    alias(libs.plugins.android.application)
    alias(libs.plugins.baselineprofile)
}

versioning {
    propertiesFile = file("../version.properties")
}

android {
    signingConfigs {
        register("release") {
            keyAlias = buildParameters.signing.key.name
            keyPassword = buildParameters.signing.key.password
            storeFile = layout.projectDirectory.file("../.keystore/${buildParameters.signing.keystore.name}").asFile
            storePassword = buildParameters.signing.keystore.password
        }
    }

    defaultConfig {
        versionCode = versioning.versionCode.get()
        versionName = versioning.versionName.get()
        minSdk = versioning.minApi.get()
    }

    buildTypes {
        debug {
            manifestPlaceholders["api-url-placeholder"] = "https://api.tuzim.xyz"
        }

        release {
            signingConfig = signingConfigs.getByName("release")

            manifestPlaceholders["api-url-placeholder"] = "https://api.tuzim.xyz"
        }

        getByName("staging") {
            initWith(getByName("debug"))
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-staging"

            manifestPlaceholders["api-url-placeholder"] = "https://staging.tuzim.xyz"
        }
    }

    lint {
        disable += "Instantiatable"
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))

    implementation(projects.core.apiCompose)
    implementation(projects.core.ui)
    implementation(projects.core.model)
    implementation(projects.core.local)
    implementation(projects.core.network)

    implementation(projects.feature.splashScreen)
    implementation(projects.feature.cvContent)
    implementation(projects.feature.easterEggs.gym)

    implementation(libs.bundles.voyager)

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.ui.tooling.preview)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.core.splashscreen)

    implementation(libs.kotlinx.serialization.json)

    // Google Play complained that I used an old version; it was probably pulled as a dependency of other libs
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.profileinstaller)

    androidTestImplementation(libs.androidx.ui.test.junit4)

    baselineProfile(projects.baselineProfile)
}
