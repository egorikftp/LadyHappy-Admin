package com.egoriku.root.koin

import com.egoriku.config.koin.configModule
import com.egoriku.main.koin.mainModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(mainModule, configModule)
    }
}