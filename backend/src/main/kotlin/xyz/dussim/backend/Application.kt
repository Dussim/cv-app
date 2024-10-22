package xyz.dussim.backend

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import xyz.dussim.backend.plugins.configureAssetLinks
import xyz.dussim.backend.plugins.configureHTTP
import xyz.dussim.backend.plugins.configureMonitoring
import xyz.dussim.backend.plugins.configureRouting
import xyz.dussim.backend.plugins.configureSerialization

fun main() {
    embeddedServer(
        factory = Netty,
        port = 80,
        host = "0.0.0.0",
        module = Application::module,
    ).start(wait = true)
}

fun Application.module() {
//    configureTemplating()
    configureSerialization()
    configureMonitoring()
    configureHTTP()
    configureRouting()
    configureAssetLinks()
}
