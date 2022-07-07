package com.egoriku.features

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.egoriku.features.domain.FeaturesItem
import com.egoriku.features.domain.store.FeaturesStore
import com.egoriku.utils.decompose.getStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class FeaturesComponentImpl(
    componentContext: ComponentContext,
    private val onClick: (String) -> Unit
) : FeaturesComponent, KoinComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { get<FeaturesStore>() }

    override val model: Flow<FeaturesComponent.Model> = store.states.map { state ->
        FeaturesComponent.Model(
            features = state.items.map { item ->
                FeaturesItem(
                    featureName = item.featureName,
                    sheetName = item.sheetName,
                    iconUrl = item.iconUrl,
                    isAvailable = item.isAvailable
                )
            },
            isLoading = state.isLoading
        )
    }

    override fun onFeatureClick(sheetName: String) {
        onClick(sheetName)
    }
}