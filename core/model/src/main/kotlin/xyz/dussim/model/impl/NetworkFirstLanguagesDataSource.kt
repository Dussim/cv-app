package xyz.dussim.model.impl

import android.util.Log
import kotlinx.coroutines.withTimeoutOrNull
import xyz.dussim.api.data.DataSource
import xyz.dussim.api.state.State
import xyz.dussim.api.state.getOrElse
import xyz.dussim.datamodel.language.Language

class NetworkFirstLanguagesDataSource(
    private val network: DataSource<State<List<Language>>>,
    private val local: DataSource<List<Language>>
) : DataSource<List<Language>> {
    companion object {
        private const val TIMEOUT = 2_000L
    }

    override suspend fun fetch(): List<Language> {
        val localResult = local.fetch()

        return withTimeoutOrNull(TIMEOUT) {
            network.fetch()
        }?.getOrElse {
            Log.e("NetworkFirstLanguagesDataSource", "Network error", it)
            localResult
        } ?: localResult
    }
}
