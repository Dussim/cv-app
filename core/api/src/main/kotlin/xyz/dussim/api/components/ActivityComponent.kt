package xyz.dussim.api.components

import android.content.Context

interface ActivityComponent {
    companion object;

    val context: Context

    val mapperComponent: MapperComponent
    val networkComponent: NetworkComponent
    val localComponent: LocalComponent
    val modelComponent: ModelComponent
}
