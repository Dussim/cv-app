package xyz.dussim.network.internal

import io.ktor.resources.*

@Resource("/cv")
internal class ApiRoutes {
    @Resource("/skills")
    internal class Skills(val parent: ApiRoutes = ApiRoutes())
}

@Resource("/ee")
internal class EasterEggsRoutes {
    @Resource("/gym-stats")
    internal class GymStats(val parent: EasterEggsRoutes = EasterEggsRoutes())
}