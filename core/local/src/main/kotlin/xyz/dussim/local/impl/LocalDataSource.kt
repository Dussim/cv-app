package xyz.dussim.local.impl

import xyz.dussim.api.data.DataSource

internal class LocalDataSource<T>(private val data: T) : DataSource<T> {
    override suspend fun fetch(): T = data
}
