package xyz.dussim.cv

import android.content.Context
import xyz.dussim.api.components.ActivityComponent
import xyz.dussim.api.components.AppComponent
import xyz.dussim.api.components.LocalComponent
import xyz.dussim.api.components.MapperComponent
import xyz.dussim.api.components.ModelComponent
import xyz.dussim.api.components.NetworkComponent
import xyz.dussim.local.impl.create
import xyz.dussim.model.impl.create
import xyz.dussim.network.internal.create

private class ActivityComponentImpl(
    appComponent: AppComponent,
    override val context: Context
) : ActivityComponent {

    override val mapperComponent = MapperComponent.create(
        activityComponent = this
    )

    override val networkComponent = NetworkComponent.create(
        mapperComponent = mapperComponent,
        dispatchersComponent = appComponent.dispatchersComponent,
        baseUrlProvider = appComponent.baseUrlProvider
    )

    override val localComponent = LocalComponent.create(
        mapperComponent = mapperComponent
    )

    override val modelComponent = ModelComponent.create(
        dispatchersComponent = appComponent.dispatchersComponent,
        localComponent = localComponent,
        networkComponent = networkComponent
    )
}

fun ActivityComponent.Companion.create(
    context: Context,
    appComponent: AppComponent
): ActivityComponent = ActivityComponentImpl(appComponent, context)
