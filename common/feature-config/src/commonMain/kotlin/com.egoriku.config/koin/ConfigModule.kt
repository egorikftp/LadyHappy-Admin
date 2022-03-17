package com.egoriku.config.koin

import com.egoriku.config.ConfigComponent
import com.egoriku.config.ConfigComponentImpl
import com.egoriku.config.ConfigViewModel
import com.egoriku.viewmodel.viewModelFactory
import org.koin.dsl.module

val configModule = module {

    viewModelFactory {
        ConfigViewModel()
    }

    factory<ConfigComponent> { (f: () -> Unit) ->
        ConfigComponentImpl(f)
    }
}