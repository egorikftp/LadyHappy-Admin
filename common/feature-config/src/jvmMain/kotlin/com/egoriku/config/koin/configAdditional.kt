package com.egoriku.config.koin

import com.egoriku.config.repository.ConfigRepository
import org.koin.dsl.module

actual val configAdditional = module {
    factory { ConfigRepository(api = get()) }
}