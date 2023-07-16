package xyz.dussim.httpclient.impl.util

internal sealed interface Maybe<out T : Any> {
    companion object {

        fun <T : Any> of(value: T?): Maybe<T> {
            return of { value }
        }

        fun <T : Any> of(provider: () -> T?): Maybe<T> {
            return try {
                when (val value = provider()) {
                    null -> Empty
                    else -> Just(value)
                }
            } catch (e: Exception) {
                Empty
            }
        }
    }

    object Empty : Maybe<Nothing>
    data class Just<T : Any>(val value: T) : Maybe<T>
}

internal fun <A : Any, B : Any> Maybe<A>.map(f: (A) -> B): Maybe<B> = when (this) {
    Maybe.Empty -> Maybe.Empty
    is Maybe.Just -> Maybe.of { f(value) }
}

internal fun <A : Any, B : Any> Maybe<A>.flatMap(f: (A) -> Maybe<B>): Maybe<B> = when (this) {
    Maybe.Empty -> Maybe.Empty
    is Maybe.Just -> f(value)
}

internal fun <A : Any> Maybe<A>.orNull(): A? = when (this) {
    Maybe.Empty -> null
    is Maybe.Just -> value
}

internal fun <A : Any, B : Any, C : Any> Maybe<(A, B) -> C>.compose2(
    a: () -> Maybe<A>
): Maybe<(B) -> C> = when (this) {
    Maybe.Empty -> Maybe.Empty
    is Maybe.Just -> when (val aProvider = a()) {
        Maybe.Empty -> Maybe.Empty
        is Maybe.Just -> Maybe.of { { b: B -> value(aProvider.value, b) } }
    }
}

internal fun <A : Any, B : Any> Maybe<(A) -> B>.compose(
    a: () -> Maybe<A>
): Maybe<B> = when (this) {
    Maybe.Empty -> Maybe.Empty
    is Maybe.Just -> when (val aProvider = a()) {
        Maybe.Empty -> Maybe.Empty
        is Maybe.Just -> Maybe.of { value(aProvider.value) }
    }
}