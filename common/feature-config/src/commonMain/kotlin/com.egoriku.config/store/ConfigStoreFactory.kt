package com.egoriku.config.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.egoriku.config.AppCoroutineDispatcher
import com.egoriku.config.store.ConfigStore.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class Repository {

    suspend fun getConfig(): List<Int> {
        return withContext(AppCoroutineDispatcher.IO) {
            delay(4000)

            List(100) { it + 1 }
        }
    }
}

internal class ConfigStoreFactory(
    private val storeFactory: StoreFactory
) {

    init {
        println("kek: ConfigStoreFactory")
    }

    private val repository = Repository()

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
            dispatch(Result.Loaded(repository.getConfig()))
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