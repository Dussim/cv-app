package xyz.dussim.network.internal.util

internal inline fun <reified E : Enum<E>> valueOrNull(value: String?): E? {
    value ?: return null
    return try {
        java.lang.Enum.valueOf(E::class.java, value)
    } catch (e: IllegalArgumentException) {
        null
    }
}

internal inline fun <reified E : Enum<E>> maybeOf(value: String?): Maybe<E> {
    return Maybe.of { valueOrNull<E>(value) }
}