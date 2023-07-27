package xyz.dussim.network.internal

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.resources.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import xyz.dussim.api.components.NetworkComponent
import xyz.dussim.api.coroutines.DispatchersComponent

private fun configureJson() = Json {
    coerceInputValues = true
    ignoreUnknownKeys = true
}

private fun configureHttpClient(serialization: Json) = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(serialization)
    }
    install(Resources)
    defaultRequest {
        url("https://rare-carp-61.deno.dev/")
    }
}

internal class NetworkComponentImpl(
    private val dispatchersComponent: DispatchersComponent
) : NetworkComponent {

    private val serialization by lazy(::configureJson)

    private val httpClient by lazy { configureHttpClient(serialization) }

    private val endpointClient by lazy { EndpointClient(httpClient) }

    override val skillsDataSource by lazy { NetworkSkillsDataSource(endpointClient, dispatchersComponent.io) }
}

public fun NetworkComponent.Companion.create(dispatchersComponent: DispatchersComponent): NetworkComponent {
    return NetworkComponentImpl(dispatchersComponent)
}