import java.util.*

plugins {
    id("xyz.dussim.android-app-convention")
    id("xyz.dussim.versioning")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.parcelize")
}

android {
    val propsFile = rootProject.file("keystore.properties")
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
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2023.06.01")

    implementation(composeBom)

    val voyagerVersion = "1.0.0-rc06"
    implementation(project(":core:api-compose"))
    implementation(project(":core:ui"))
    implementation(project(":core:model"))

    implementation(project(":core:local"))
    implementation(project(":core:network"))

    implementation(project(":feature:splash-screen"))
    implementation(project(":feature:cv-content"))

    implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.foundation:foundation")

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.31.4-beta")

    implementation("androidx.core:core-splashscreen:1.0.1")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    // google play complained that I used old version, it was probably pulled as dependency of other libs
    implementation("androidx.fragment:fragment-ktx:1.6.1")

    androidTestImplementation(composeBom)
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    instantAppImplementation("com.google.android.gms:play-services-instantapps:18.0.1")
}
