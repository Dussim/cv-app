package xyz.dussim.network.internal

import io.ktor.resources.*

@Resource("/api")
internal class ApiRoutes {
    @Resource("/skills")
    internal class Skills(val parent: ApiRoutes = ApiRoutes())
}