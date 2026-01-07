plugins {
    alias(conventions.plugins.xyz.dussim.multiplatform.library.convention)
}

android {
    namespace = "xyz.dussim.datamodel"

    resourcePrefix = "data_model_"

    sourceSets.getByName("main") {
        res.srcDirs(
            "src/androidMain/res",
            "src/commonMain/resources",
        )
    }
}

group = "xyz.dussim"
version = "1.0.0"
