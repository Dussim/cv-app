package xyz.dussim.network.internal

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import xyz.dussim.api.data.DataSource
import xyz.dussim.api.state.State
import xyz.dussim.datamodel.UniversalMapper
import xyz.dussim.datamodel.language.Language

internal class NetworkLanguagesDataSource(
    private val endpointClient: EndpointClient,
    private val universalMapper: UniversalMapper,
    private val io: CoroutineDispatcher,
) : DataSource<State<List<Language>>> {
    override suspend fun fetch(): State<List<Language>> =
        withContext(io) {
            endpointClient
                .fetchLanguages()
                .map(universalMapper::mapLanguages)
                .map { State.Success(it) }
                .getOrElse { State.Error(it) }
        }
}
