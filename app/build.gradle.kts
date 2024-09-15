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

        create("staging") {
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
    val composeBom = platform("androidx.compose:compose-bom:2024.09.01")

    implementation(composeBom)

    val voyagerVersion = "1.0.0"
    implementation(project(":core:api-compose"))
    implementation(project(":core:ui"))
    implementation(project(":core:model"))
    implementation(project(":core:local"))
    implementation(project(":core:network"))

    implementation(project(":feature:splash-screen"))
    implementation(project(":feature:cv-content"))
    implementation(project(":feature:easter-eggs:gym"))

    implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")
    implementation("cafe.adriel.voyager:voyager-screenmodel:$voyagerVersion")
    implementation("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.foundation:foundation")

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.5")
    implementation("androidx.activity:activity-compose:1.9.2")

    implementation("androidx.core:core-splashscreen:1.0.1")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.2")

    // Google Play complained that I used an old version; it was probably pulled as a dependency of other libs
    implementation("androidx.fragment:fragment-ktx:1.8.3")

    androidTestImplementation(composeBom)
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    "instantAppImplementation"("com.google.android.gms:play-services-instantapps:18.1.0")
}
