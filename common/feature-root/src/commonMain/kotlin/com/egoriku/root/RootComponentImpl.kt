package com.egoriku.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.egoriku.main.MainComponent
import com.egoriku.root.RootComponent.Child
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

class RootComponentImpl(
    componentContext: ComponentContext
) : RootComponent, KoinComponent, ComponentContext by componentContext {

    private val router = router<Configuration, Child>(
        initialConfiguration = Configuration.Main,
        handleBackButton = true,
        key = "Root",
        childFactory = { configuration, componentContext ->
            when (configuration) {
                is Configuration.Main -> Child.Main(
                    mainComponent(componentContext = componentContext)
                )
                // is Configuration.Login -> Child.Login
            }
        }
    )

    private fun mainComponent(componentContext: ComponentContext) =
        get<MainComponent> { parametersOf(componentContext) }

    override val routerState: Value<RouterState<*, Child>> = router.state

    private sealed class Configuration : Parcelable {
        @Parcelize
        object Main : Configuration()

        /* @Parcelize
         object Login : Configuration()*/
    }
}