package xyz.dussim.cv.data

import androidx.compose.runtime.Immutable

@Immutable
class ImList<E>(private val list: List<E>) : List<E> by list

fun <E> List<E>.toImmutable() = ImList(this)
