package xyz.dussim.network.internal

import io.ktor.resources.*

@Resource("/cv")
internal class ApiRoutes {
    @Resource("/skills")
    internal class Skills(val parent: ApiRoutes = ApiRoutes())
}