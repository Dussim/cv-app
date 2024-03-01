package xyz.dussim.api.components

import android.content.Context
import xyz.dussim.api.coroutines.DispatchersComponent

interface AppComponent {
    val appContext: Context
    val baseUrlProvider: BaseUrlProvider
    val dispatchersComponent: DispatchersComponent
}
