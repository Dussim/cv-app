package xyz.dussim.network.internal

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
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
    explicitNulls = false
}

private fun configureHttpClient(serialization: Json) = HttpClient(OkHttp) {
    install(ContentNegotiation) {
        json(serialization)
    }
    install(Resources)
    defaultRequest {
        url("https://api.tuzim.xyz")
    }
}

internal class NetworkComponentImpl(
    private val dispatchersComponent: DispatchersComponent
) : NetworkComponent {

    private val serialization by lazy(::configureJson)

    private val httpClient by lazy { configureHttpClient(serialization) }

    private val endpointClient by lazy { EndpointClient(httpClient) }

    override val skillsDataSource by lazy { NetworkSkillsDataSource(endpointClient, dispatchersComponent.io) }

    override val gymStatsDataSource by lazy { NetworkGymStatsDataSource(endpointClient, dispatchersComponent.io) }
}

fun NetworkComponent.Companion.create(dispatchersComponent: DispatchersComponent): NetworkComponent {
    return NetworkComponentImpl(dispatchersComponent)
}