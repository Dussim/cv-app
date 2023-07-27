package xyz.dussim.api.data

fun interface DataSource<out T> {
    suspend fun fetch(): T
}

