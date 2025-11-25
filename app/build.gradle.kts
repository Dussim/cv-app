plugins {
    alias(conventions.plugins.xyz.dussim.android.app.convention)
    alias(conventions.plugins.xyz.dussim.build.parameters)
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

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.splashscreen)

    releaseImplementation(libs.androidx.profileinstaller)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    baselineProfile(projects.baselineProfile)
}

configurations.configureEach {
    exclude(group = "androidx.fragment", module = "fragment-ktx")
}
