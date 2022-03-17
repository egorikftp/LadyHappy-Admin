package com.egoriku.main.koin

import com.arkivanov.decompose.ComponentContext
import com.egoriku.main.MainComponent
import com.egoriku.main.MainComponentImpl
import org.koin.dsl.module

val mainModule = module {
    factory<MainComponent> { (componentContext: ComponentContext) ->
        MainComponentImpl(componentContext = componentContext)
    }
}