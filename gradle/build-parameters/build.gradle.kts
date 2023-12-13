plugins {
    id("org.gradlex.build-parameters").version("1.4.3")
}

buildParameters {
    pluginId("xyz.dussim.build-parameters")

    bool("ci") {
        fromEnvironment()
        defaultValue = false
        description = "True if the build is running in CI environment"
    }
    group("signing") {
        group("keystore") {
            string("name") {
                defaultValue = "debug.jks"
                description = "Name of the keystore to use"
            }
            string("password") {
                defaultValue = "debug-dummy"
                description = "Password for the keystore to use"
            }
        }
        group("key") {
            string("name") {
                defaultValue = "debug"
                description = "Name of the key to use"
            }
            string("password") {
                defaultValue = "debug-dummy"
                description = "Password for the key to use"
            }
        }
    }
    group("cache") {
        string("url") {
            description = "Url of remote cache node."
            defaultValue = "https://0885225.xyz/cache/"
        }
        string("username") {
            description = "Username used for remote cache read/write user. Default for read only user"
            defaultValue = "build-cache-r"
        }
        string("password") {
            description = "Password used for remote cache read/write user. Default for read only user"
            defaultValue = ",79'2V`?2CuC"
        }
    }
}