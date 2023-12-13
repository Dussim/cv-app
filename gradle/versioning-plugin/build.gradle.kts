plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        create("versioning") {
            id = "xyz.dussim.versioning"
            implementationClass = "xyz.dussim.buildlogic.VersioningPlugin"
        }
    }
}

group = "xyz.dussim"
version = "1.0.0"