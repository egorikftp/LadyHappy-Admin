package com.egoriku.main

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.egoriku.config.ConfigComponent
import com.egoriku.features.FeaturesComponent

interface MainComponent {

    val routerState: Value<RouterState<*, Child>>

    sealed class Child {
        data class Features(val component: FeaturesComponent) : Child()
        data class Config(val component: ConfigComponent) : Child()
    }
}