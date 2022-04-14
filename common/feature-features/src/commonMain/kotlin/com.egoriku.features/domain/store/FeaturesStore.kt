package com.egoriku.features.domain.store

import com.arkivanov.mvikotlin.core.store.Store
import com.egoriku.features.data.Features

internal interface FeaturesStore : Store<FeaturesStore.Intent, FeaturesStore.State, Nothing> {

    sealed class Intent

    sealed class Result {
        object Loading : Result()
        data class Loaded(val list: List<Features>) : Result()
    }

    data class State(
        val isLoading: Boolean = false,
        val items: List<Features> = emptyList()
    )
}