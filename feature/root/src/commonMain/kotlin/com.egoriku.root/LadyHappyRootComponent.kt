package com.egoriku.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.pop
import com.arkivanov.decompose.router.push
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.egoriku.root.LadyHappyRoot.LadyHappyChild
import com.egoriku.root.config.ConfigComponent
import com.egoriku.root.main.MainScreenComponent

class LadyHappyRootComponent(
    componentContext: ComponentContext
) : LadyHappyRoot, ComponentContext by componentContext {

    private val router = router<Configuration, LadyHappyChild>(
        initialConfiguration = Configuration.Main,
        handleBackButton = true,
        childFactory = ::createChild
    )

    override val routerState: Value<RouterState<*, LadyHappyChild>> = router.state

    private fun createChild(
        configuration: Configuration,
        componentContext: ComponentContext
    ): LadyHappyChild =
        when (configuration) {
            is Configuration.Main -> LadyHappyChild.Main(mainScreenComponent())
            is Configuration.Config -> LadyHappyChild.Config(configComponent())
        }

    private fun mainScreenComponent() = MainScreenComponent(
        onClick = { router.push(Configuration.Config) }
    )

    private fun configComponent() = ConfigComponent(
        onClose = { router.pop() }
    )

    private sealed class Configuration : Parcelable {
        @Parcelize
        object Main : Configuration()

        @Parcelize
        object Config : Configuration()
    }
}