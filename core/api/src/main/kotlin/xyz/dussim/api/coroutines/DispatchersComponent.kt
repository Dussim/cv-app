package xyz.dussim.api.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatchersComponent {
    companion object;

    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}

private class DispatchersComponentImpl(
    override val main: CoroutineDispatcher = Dispatchers.Main,
    override val io: CoroutineDispatcher = Dispatchers.IO,
    override val default: CoroutineDispatcher = Dispatchers.Default,
    override val unconfined: CoroutineDispatcher = Dispatchers.Unconfined
) : DispatchersComponent

fun DispatchersComponent.Companion.create(): DispatchersComponent = DispatchersComponentImpl()