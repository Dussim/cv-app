package xyz.dussim.api.data

import kotlinx.coroutines.flow.MutableStateFlow

fun <T> DataSource<T>.withState(initial: T): StateSource<T> {
    return object : StateSource<T> {
        override val state = MutableStateFlow(initial)

        override suspend fun fetch(): T {
            return this@withState.fetch().also { state.value = it }
        }
    }
}
