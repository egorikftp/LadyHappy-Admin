package com.egoriku.config.koin

import com.arkivanov.decompose.ComponentContext
import com.egoriku.config.ConfigComponent
import com.egoriku.config.ConfigComponentImpl
import com.egoriku.config.ConfigViewModel
import com.egoriku.config.store.ConfigStore
import com.egoriku.config.store.ConfigStoreFactory
import com.egoriku.viewmodel.viewModelFactory
import org.koin.dsl.module

val configModule = module {

    viewModelFactory {
        ConfigViewModel()
    }

    factory<ConfigComponent> { (componentContext: ComponentContext, onBack: () -> Unit) ->
        ConfigComponentImpl(componentContext = componentContext, onBack = onBack)
    }

    factory {
        ConfigStoreFactory(storeFactory = get()).create()
    }
}