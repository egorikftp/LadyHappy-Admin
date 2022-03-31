package com.egoriku.root.koin

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.egoriku.main.koin.mainModule
import com.egoriku.network.koin.networkModule
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() {
    startKoin {
        modules(
            mainModule,
            mviModule,
            networkModule
        )
    }
}

val mviModule = module {
    single<StoreFactory> { DefaultStoreFactory }
}