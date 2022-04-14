package com.egoriku.features.koin

import com.egoriku.features.data.repository.FeaturesRepository
import com.egoriku.features.domain.store.FeaturesStoreFactory
import org.koin.dsl.module

val featuresModule = module {
    factory {
        FeaturesStoreFactory(
            storeFactory = get(),
            featuresRepository = get()
        ).create()
    }

    factory { FeaturesRepository(httpClient = get()) }
}