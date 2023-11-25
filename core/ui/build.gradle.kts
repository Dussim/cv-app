plugins {
    xyz.dussim.android.library.compose.convention
}

android {
    namespace = "xyz.dussim.ui"
}

dependencies {
    api(project(":core:data"))
    api(project(":core:design-system"))
}
