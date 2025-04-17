plugins {
    id("xyz.dussim.android.app.convention")
    id("xyz.dussim.build-parameters")
}

versioning {
    propertiesFile = file("../version.properties")
}

android {
    signingConfigs {
        create("release") {
            keyAlias = buildParameters.signing.key.name
            keyPassword = buildParameters.signing.key.password
            storeFile = layout.projectDirectory.file("../.keystore/${buildParameters.signing.keystore.name}").asFile
            storePassword = buildParameters.signing.keystore.password
        }
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

    flavorDimensions.add("installationType")

    productFlavors {
        create("instantApp") {
            dimension = "installationType"
            versionCode = versioning.versionCode.get()
            versionNameSuffix = "-instant"
        }

        create("installedApp") {
            dimension = "installationType"
            versionCode = versioning.versionCode.get() + 60
        }

        forEach {
            it.versionName = versioning.versionName.get()
            it.minSdk = versioning.minApi.get()
        }
    }

    lint {
        disable += "Instantiatable"
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2025.04.00")

    implementation(composeBom)

    implementation(project(":core:api-compose"))
    implementation(project(":core:ui"))
    implementation(project(":core:model"))
    implementation(project(":core:local"))
    implementation(project(":core:network"))

    implementation(project(":feature:splash-screen"))
    implementation(project(":feature:cv-content"))
    implementation(project(":feature:easter-eggs:gym"))

    implementation(libs.bundles.voyager)

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.foundation)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.core.splashscreen)

    implementation(libs.kotlinx.serialization.json)

    // Google Play complained that I used an old version; it was probably pulled as a dependency of other libs
    implementation(libs.androidx.fragment.ktx)

    androidTestImplementation(composeBom)
    androidTestImplementation(libs.androidx.ui.test.junit4)

    "instantAppImplementation"(libs.play.services.instantapps)
}
