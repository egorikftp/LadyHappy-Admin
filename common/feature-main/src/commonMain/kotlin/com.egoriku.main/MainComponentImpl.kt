package com.egoriku.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.router.pop
import com.arkivanov.decompose.router.push
import com.arkivanov.decompose.router.router
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.egoriku.config.ConfigComponent
import com.egoriku.features.FeaturesComponent
import com.egoriku.features.FeaturesComponentImpl
import com.egoriku.main.MainComponent.Child
import com.egoriku.main.MainComponentImpl.Configuration.Config
import com.egoriku.main.MainComponentImpl.Configuration.Features
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class MainComponentImpl(
    componentContext: ComponentContext
) : MainComponent, KoinComponent, ComponentContext by componentContext {

    private val configComponent by inject<ConfigComponent> {
        parametersOf(::goBack)
    }

    private val router = router<Configuration, Child>(
        initialConfiguration = Features,
        handleBackButton = true,
        key = "Main",
        childFactory = { configuration, _ ->
            when (configuration) {
                is Features -> Child.Features(component = featuresComponent())
                is Config -> Child.Config(component = configComponent)
            }
        }
    )

    private fun goBack() {
        router.pop()
    }

    private fun featuresComponent(): FeaturesComponent = FeaturesComponentImpl(
        onClick = {
            router.push(Config)
        })

    override val routerState: Value<RouterState<*, Child>> = router.state

    private sealed class Configuration : Parcelable {
        @Parcelize
        object Features : Configuration()

        @Parcelize
        object Config : Configuration()

    }
}