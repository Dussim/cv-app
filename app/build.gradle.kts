import java.util.*

plugins {
    xyz.dussim.android.app.convention
    xyz.dussim.versioning
    kotlin("android")
    kotlin("plugin.parcelize")
}

android {
    rootProject.file("keystore.properties").takeIf { it.exists() }?.let { propsFile ->
        val props = Properties().apply {
            load(propsFile.reader())
        }
        signingConfigs {
            create("release") {
                keyAlias = props["alias"] as String
                keyPassword = props["password"] as String
                storeFile = rootProject.file(props["location"] as String)
                storePassword = props["password"] as String
            }
        }
    } ?: run {
        println(
            "Keystore file not found. Please create a `keystore.properties` file in the root project directory."
        )
    }

    defaultConfig {
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        val release by getting {
            signingConfigs.findByName("release")?.let { releaseConfig ->
                signingConfig = releaseConfig
            }
        }
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2023.06.01")

    implementation(composeBom)

    val voyagerVersion = "1.0.0-rc10"
    implementation(project(":core:api-compose"))
    implementation(project(":core:ui"))
    implementation(project(":core:model"))
    implementation(project(":core:local"))
    implementation(project(":core:network"))

    implementation(project(":feature:splash-screen"))
    implementation(project(":feature:cv-content"))
    implementation(project(":feature:easter-eggs:gym"))

    implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")
    implementation("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.foundation:foundation")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.32.0")

    implementation("androidx.core:core-splashscreen:1.0.1")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    // google play complained that I used old version, it was probably pulled as dependency of other libs
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    androidTestImplementation(composeBom)
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    instantAppImplementation("com.google.android.gms:play-services-instantapps:18.0.1")
}
