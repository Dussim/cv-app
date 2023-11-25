plugins {
    xyz.dussim.android.library.convention
    kotlin("plugin.parcelize")
}

android {
    namespace = "xyz.dussim.data"
}

dependencies {
    api("androidx.annotation:annotation:1.6.0")
}
