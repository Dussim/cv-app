plugins {
    id("xyz.dussim.android-library-compose-convention")
}

android {
    namespace = "xyz.dussim.navigation"
}

dependencies {
    val voyagerVersion = "1.0.0-rc06"
    api(project(":core:data"))

    api("cafe.adriel.voyager:voyager-core:$voyagerVersion")
}
