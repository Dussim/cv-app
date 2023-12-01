package xyz.dussim.model.impl

import android.util.Log
import kotlinx.coroutines.withTimeoutOrNull
import xyz.dussim.api.data.DataSource
import xyz.dussim.api.state.State
import xyz.dussim.api.state.getOrElse
import xyz.dussim.data.skills.Skill

internal class NetworkFirstSkillsDataSource(
    private val network: DataSource<State<List<Skill>>>,
    private val local: DataSource<List<Skill>>
) : DataSource<List<Skill>> {
    companion object {
        private const val TIMEOUT = 20_000L
    }

    override suspend fun fetch(): List<Skill> {
        val localResult = local.fetch()

        return withTimeoutOrNull(TIMEOUT) {
            network.fetch()
        }?.getOrElse {
            Log.e("NetworkFirstSkillsDataSource", "Network error", it)
            localResult
        } ?: localResult
    }
}