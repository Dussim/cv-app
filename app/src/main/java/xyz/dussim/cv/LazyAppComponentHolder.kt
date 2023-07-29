package xyz.dussim.cv

import xyz.dussim.api.components.*
import xyz.dussim.api.coroutines.DispatchersComponent
import xyz.dussim.api.coroutines.create
import xyz.dussim.local.impl.create
import xyz.dussim.model.impl.create
import xyz.dussim.network.internal.create

internal data object LazyAppComponentHolder : AppComponentHolder {
    private class AppComponentImpl(
        override val modelComponent: ModelComponent,
        override val localComponent: LocalComponent,
        override val networkComponent: NetworkComponent
    ) : AppComponent

    override val appComponent: AppComponent by lazy {
        val dispatchersComponent = DispatchersComponent.create()
        val localComponent = LocalComponent.create()
        val networkComponent = NetworkComponent.create(dispatchersComponent)

        val modelComponent = ModelComponent.create(
            dispatchersComponent = dispatchersComponent,
            localComponent = localComponent,
            networkComponent = networkComponent
        )

        AppComponentImpl(
            modelComponent = modelComponent,
            localComponent = localComponent,
            networkComponent = networkComponent
        )
    }
}
