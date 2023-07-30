package xyz.dussim.api.components

interface AppComponent {
    val modelComponent: ModelComponent
    val localComponent: LocalComponent
    val networkComponent: NetworkComponent
}