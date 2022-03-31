package com.egoriku.config.koin

import com.arkivanov.decompose.ComponentContext
import com.egoriku.config.ConfigComponent
import com.egoriku.config.ConfigComponentImpl
import com.egoriku.config.repository.ConfigRepository
import com.egoriku.config.store.ConfigStoreFactory
import org.koin.dsl.module

val configModule = module {
    factory { ConfigRepository(httpClient = get()) }

    factory<ConfigComponent> { (componentContext: ComponentContext, onBack: () -> Unit) ->
        ConfigComponentImpl(componentContext = componentContext, onBack = onBack)
    }

    factory {
        ConfigStoreFactory(
            storeFactory = get(),
            configRepository = get()
        ).create()
    }
}