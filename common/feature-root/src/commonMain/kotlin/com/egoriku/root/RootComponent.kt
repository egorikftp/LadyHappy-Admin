package com.egoriku.root

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.egoriku.main.MainComponent

interface RootComponent {

    val routerState: Value<RouterState<*, Child>>

    sealed class Child {
        data class Main(val component: MainComponent) : Child()
        // object Login : Child()
    }
}