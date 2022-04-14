package com.egoriku.features.domain.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.egoriku.features.data.repository.FeaturesRepository
import com.egoriku.utils.AppCoroutineDispatcher

internal class FeaturesStoreFactory(
    private val storeFactory: StoreFactory,
    private val featuresRepository: FeaturesRepository
) {

    sealed class InitialAction {
        object Initial : InitialAction()
    }

    fun create(): FeaturesStore = object : FeaturesStore,
        Store<FeaturesStore.Intent, FeaturesStore.State, Nothing> by storeFactory.create(
            name = FeaturesStore::class.simpleName,
            initialState = FeaturesStore.State(),
            bootstrapper = SimpleBootstrapper(InitialAction.Initial),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private inner class ExecutorImpl :
        SuspendExecutor<
                FeaturesStore.Intent,
                InitialAction,
                FeaturesStore.State,
                FeaturesStore.Result,
                Nothing>(AppCoroutineDispatcher.Main) {

        override suspend fun executeIntent(
            intent: FeaturesStore.Intent,
            getState: () -> FeaturesStore.State
        ) {
            super.executeIntent(intent, getState)
        }

        override suspend fun executeAction(
            action: InitialAction,
            getState: () -> FeaturesStore.State
        ) {
            when (action) {
                InitialAction.Initial -> loadConfig()
            }
        }

        private suspend fun loadConfig() {
            dispatch(FeaturesStore.Result.Loading)
            dispatch(FeaturesStore.Result.Loaded(featuresRepository.load()))
        }
    }

    object ReducerImpl : Reducer<FeaturesStore.State, FeaturesStore.Result> {
        override fun FeaturesStore.State.reduce(result: FeaturesStore.Result): FeaturesStore.State =
            when (result) {
                is FeaturesStore.Result.Loaded -> copy(items = result.list, isLoading = false)
                is FeaturesStore.Result.Loading -> copy(isLoading = true)
            }
    }
}