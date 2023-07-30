package xyz.dussim.api.state

sealed interface State<out T> {
    companion object {
        fun <T> loading(): State<T> = Loading
        fun <T> error(error: Throwable): State<T> = Error(error)
        fun <T> success(value: T): State<T> = Success(value)
    }

    data object Loading : State<Nothing>
    data class Error(val error: Throwable) : State<Nothing>
    data class Success<T>(val value: T) : State<T>
}

fun <T> State<T>.orElse(recover: (Throwable) -> State<T>): State<T> = when (this) {
    is State.Loading, is State.Success -> this
    is State.Error -> recover(error)
}

fun <T> State<T>.orDefault(recover: (Throwable) -> T): State<T> = when (this) {
    is State.Loading -> this
    is State.Success -> this
    is State.Error -> State.Success(recover(error))
}

fun <T> State<T>.getOrThrow(): T = when (this) {
    is State.Loading -> throw IllegalStateException("Loading state has no value")
    is State.Error -> throw error
    is State.Success -> value
}

fun <T> State<T>.getOrElse(recover: (Throwable) -> T): T = when (this) {
    is State.Loading -> recover(IllegalStateException("Loading state has no value"))
    is State.Error -> recover(error)
    is State.Success -> value
}

fun <T> State<T>.getOrDefault(default: T): T = when (this) {
    is State.Loading -> default
    is State.Error -> default
    is State.Success -> value
}