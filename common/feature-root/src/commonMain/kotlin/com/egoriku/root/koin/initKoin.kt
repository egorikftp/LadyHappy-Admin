package com.egoriku.root.koin

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.egoriku.config.koin.configModule
import com.egoriku.main.koin.mainModule
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() {
    startKoin {
        modules(
            mainModule,
            mviModule,
            configModule
        )
    }
}

val mviModule = module {
    single<StoreFactory> { DefaultStoreFactory }
}