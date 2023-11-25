import java.util.*

plugins {
    xyz.dussim.module.utilities
}

tasks.register("incrementMajor") {
    group = "versioning"

    doLast {
        Properties().apply {
            load(rootProject.file("version.properties").inputStream())
            val major = getProperty("major").toInt()
            setProperty("major", (major + 1).toString())
            setProperty("minor", "0")
            store(rootProject.file("version.properties").outputStream(), null)
        }
    }
}
tasks.register("incrementMinor") {
    group = "versioning"

    doLast {
        Properties().apply {
            load(rootProject.file("version.properties").inputStream())
            val major = getProperty("minor").toInt()
            setProperty("minor", (major + 1).toString())
            store(rootProject.file("version.properties").outputStream(), null)
        }
    }
}
