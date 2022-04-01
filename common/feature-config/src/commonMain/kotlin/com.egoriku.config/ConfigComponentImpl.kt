package com.egoriku.config

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.egoriku.config.ConfigComponent.Model
import com.egoriku.config.store.ConfigStore
import com.egoriku.utils.decompose.getStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

internal class ConfigComponentImpl(
    private val componentContext: ComponentContext,
    private val onBack: () -> Unit
) : ConfigComponent, KoinComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { get<ConfigStore>() }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: Flow<Model> = store.states.map { state: ConfigStore.State ->
        Model(
            config = state.items,
            isLoading = state.isLoading
        )
    }

    override fun onCloseClicked() = onBack()
}
