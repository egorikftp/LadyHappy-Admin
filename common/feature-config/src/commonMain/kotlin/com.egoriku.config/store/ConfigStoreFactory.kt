package com.egoriku.config.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.egoriku.config.repository.ConfigRepository
import com.egoriku.config.store.ConfigStore.*
import com.egoriku.utils.AppCoroutineDispatcher

internal class ConfigStoreFactory(
    private val storeFactory: StoreFactory,
    private val configRepository: ConfigRepository
) {

    fun create(): ConfigStore = object : ConfigStore,
        Store<Intent, State, Nothing> by storeFactory.create(
            name = ConfigStore::class.simpleName,
            initialState = State(),
            bootstrapper = SimpleBootstrapper(InitialAction.Initial),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private inner class ExecutorImpl :
        SuspendExecutor<
                Intent,
                InitialAction,
                State,
                Result,
                Nothing>(AppCoroutineDispatcher.Main) {

        override suspend fun executeIntent(intent: Intent, getState: () -> State) {
            super.executeIntent(intent, getState)
        }

        override suspend fun executeAction(action: InitialAction, getState: () -> State) {
            when (action) {
                InitialAction.Initial -> loadConfig()
            }
        }

        private suspend fun loadConfig() {
            dispatch(Result.Loading)
            dispatch(Result.Loaded(configRepository.loadConfig()))
        }
    }

    object ReducerImpl : Reducer<State, Result> {
        override fun State.reduce(result: Result): State = when (result) {
            is Result.Loaded -> copy(items = result.items, isLoading = false)
            is Result.Loading -> copy(isLoading = true)
        }
    }
}

sealed class InitialAction {
    object Initial : InitialAction()
}