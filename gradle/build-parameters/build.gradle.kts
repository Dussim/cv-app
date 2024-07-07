plugins {
    id("org.gradlex.build-parameters").version("1.4.4")
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
                defaultValue = "dummy"
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
            fromEnvironment("BUILD_CACHE_URL")
            description = "Url of remote cache node."
            defaultValue = "https://build-cache.dussim.xyz/cache/"
        }
        string("username") {
            fromEnvironment("BUILD_CACHE_USER")
            description = "Username used for remote cache read/write user. Default for read only user"
            defaultValue = "build-cache-r"
        }
        string("password") {
            fromEnvironment("BUILD_CACHE_USER_PASSWORD")
            description = "Password used for remote cache read/write user. Default for read only user"
            defaultValue = ",79'2V`?2CuC"
        }
    }

    group("deployment") {
        group("ssh") {
            string("host") {
                description = "Host to deploy to"
                defaultValue = "192.168.88.253"
            }
            string("user") {
                description = "User to deploy as"
                defaultValue = "dussim"
            }
        }
    }
}