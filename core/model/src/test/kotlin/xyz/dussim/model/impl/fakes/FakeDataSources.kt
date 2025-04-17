package xyz.dussim.model.impl.fakes

import kotlinx.coroutines.delay
import xyz.dussim.api.data.DataSource
import xyz.dussim.api.state.State

/**
 * Fake data source implementations for testing.
 *
 * A fake data source that returns the provided data.
 */
class FakeDataSource<T>(private val data: T) : DataSource<T> {
    override suspend fun fetch(): T = data
}

/**
 * A fake data source that returns a successful state with the provided data.
 */
class FakeSuccessNetworkDataSource<T>(private val data: T) : DataSource<State<T>> {
    override suspend fun fetch(): State<T> = State.Success(data)
}

/**
 * A fake data source that returns an error state with the provided error.
 */
class FakeFailureNetworkDataSource<T>(private val error: Throwable) : DataSource<State<T>> {
    override suspend fun fetch(): State<T> = State.Error(error)
}

/**
 * A fake data source that simulates a timeout by suspending indefinitely.
 */
class FakeTimeoutNetworkDataSource<T> : DataSource<State<T>> {
    override suspend fun fetch(): State<T> {
        // Simulate a timeout by suspending indefinitely
        delay(Long.MAX_VALUE)
        return State.Error(RuntimeException("This should never be reached"))
    }
}

/**
 * A fake data source that returns the provided local data
 */
class FakeLocalDataSource<T>(private val data: T) : DataSource<T> {
    override suspend fun fetch(): T = data
}
