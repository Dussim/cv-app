package xyz.dussim.httpclient.impl

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import xyz.dussim.httpclient.api.HttpComponent
import xyz.dussim.httpclient.api.SkillsApiEndpoint


private fun configureJson() = Json {
    coerceInputValues = true
}

private fun configureHttpClient(serialization: Json) = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(serialization)
    }
    defaultRequest {
        url("https://rare-carp-61.deno.dev/")
    }
}

internal class HttpComponentImpl(
) : HttpComponent {

    private val serialization by lazy(::configureJson)

    private val httpClient by lazy { configureHttpClient(serialization) }

    private val endpointClient by lazy { EndpointClient(httpClient) }

    override val skillsApiEndpoint: SkillsApiEndpoint by lazy { SkillsApiEndpointImpl(endpointClient) }
}

public fun HttpComponent.Companion.create(): HttpComponent = HttpComponentImpl()