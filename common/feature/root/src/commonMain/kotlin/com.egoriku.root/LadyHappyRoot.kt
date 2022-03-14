package com.egoriku.root

import com.arkivanov.decompose.router.RouterState
import com.arkivanov.decompose.value.Value
import com.egoriku.root.config.ConfigScreen
import com.egoriku.root.main.MainScreen

interface LadyHappyRoot {

    val routerState: Value<RouterState<*, LadyHappyChild>>

    sealed class LadyHappyChild {
        data class Main(val component: MainScreen) : LadyHappyChild()
        data class Config(val component: ConfigScreen) : LadyHappyChild()
    }
}