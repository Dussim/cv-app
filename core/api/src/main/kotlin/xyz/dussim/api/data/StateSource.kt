package xyz.dussim.api.data

import kotlinx.coroutines.flow.StateFlow

interface StateSource<out T> : DataSource<T> {
    val currentValue: T get() = state.value

    val state: StateFlow<T>
}