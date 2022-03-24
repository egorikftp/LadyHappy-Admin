package com.egoriku.config

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.egoriku.config.ConfigComponent.Model
import com.egoriku.config.store.ConfigStore
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
            categories = state.items,
            isLoading = state.isLoading
        )
    }

    override fun onCloseClicked() = onBack()
}

fun <T : Store<*, *, *>> InstanceKeeper.getStore(key: Any, factory: () -> T): T =
    getOrCreate(key) { StoreHolder(factory()) }
        .store

inline fun <reified T : Store<*, *, *>> InstanceKeeper.getStore(noinline factory: () -> T): T =
    getStore(T::class, factory)

private class StoreHolder<T : Store<*, *, *>>(
    val store: T
) : InstanceKeeper.Instance {
    override fun onDestroy() {
        store.dispose()
    }
}
