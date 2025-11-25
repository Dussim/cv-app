package xyz.dussim.network.internal

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.resources.Resources
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import xyz.dussim.api.components.BaseUrlProvider
import xyz.dussim.api.components.MapperComponent
import xyz.dussim.api.components.NetworkComponent
import xyz.dussim.api.coroutines.DispatchersComponent

private fun configureJson() =
    Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
        explicitNulls = false
    }

private fun configureHttpClient(
    serialization: Json,
    baseUrlProvider: BaseUrlProvider,
) = HttpClient(Android) {
    install(ContentNegotiation) {
        json(serialization)
    }
    install(Resources)
    defaultRequest {
        url(baseUrlProvider.getBaseUrl())
    }
}

internal class NetworkComponentImpl(
    private val mapperComponent: MapperComponent,
    private val dispatchersComponent: DispatchersComponent,
    baseUrlProvider: BaseUrlProvider,
) : NetworkComponent {
    private val serialization by lazy(::configureJson)

    private val httpClient by lazy { configureHttpClient(serialization, baseUrlProvider) }

    private val endpointClient by lazy { EndpointClient(httpClient) }

    override val skillsDataSource by lazy {
        NetworkSkillsDataSource(
            endpointClient,
            mapperComponent.universalMapper,
            dispatchersComponent.io,
        )
    }

    override val leanguagesDataSource by lazy {
        NetworkLanguagesDataSource(
            endpointClient,
            mapperComponent.universalMapper,
            dispatchersComponent.io,
        )
    }

    override val gymStatsDataSource by lazy {
        NetworkGymStatsDataSource(
            endpointClient,
            dispatchersComponent.io,
        )
    }
}

fun NetworkComponent.Companion.create(
    mapperComponent: MapperComponent,
    dispatchersComponent: DispatchersComponent,
    baseUrlProvider: BaseUrlProvider,
): NetworkComponent =
    NetworkComponentImpl(
        mapperComponent = mapperComponent,
        dispatchersComponent = dispatchersComponent,
        baseUrlProvider = baseUrlProvider,
    )
