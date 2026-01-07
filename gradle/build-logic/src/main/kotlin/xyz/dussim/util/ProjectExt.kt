package xyz.dussim.util

import org.gradle.accessors.dm.LibrariesForKtor
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.api.plugins.PluginManager
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.the
import org.gradle.plugin.use.PluginDependency

internal val Project.libs get() = the<LibrariesForLibs>()
internal val Project.ktor get() = the<LibrariesForKtor>()

fun PluginManager.apply(pluginId: Provider<PluginDependency>) = apply(pluginId.get().pluginId)
