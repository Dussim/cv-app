package xyz.dussim.buildlogic.internal

import java.io.File
import java.util.Properties
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal class FilePropertiesDelegate(private val file: File) : ReadOnlyProperty<Any?, Properties> {
    private val props = Properties()
    private val File.isDirty get() = lastModified() != lastSync

    private var lastSync = 0L

    override fun getValue(thisRef: Any?, property: KProperty<*>): Properties {
        if (file.isDirty) reload()
        return props
    }

    private fun reload() {
        try {
            file.takeIf(File::isFile)?.reader()?.use(props::load)
        } finally {
            lastSync = file.lastModified()
        }
    }
}