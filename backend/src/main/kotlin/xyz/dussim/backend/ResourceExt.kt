package xyz.dussim.backend

import org.springframework.core.io.Resource

fun Resource.readAsString() = inputStream.bufferedReader().readText()