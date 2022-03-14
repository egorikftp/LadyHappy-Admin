package com.egoriku.root.koin

import com.egoriku.root.config.network.ConfigViewModel
import com.egoriku.viewmodel.viewModelFactory
import org.koin.core.context.startKoin
import org.koin.dsl.module

val viewModels = module {

    viewModelFactory {
        ConfigViewModel()
    }
}

fun initKoin() {
    startKoin {
        modules(viewModels)
    }
}