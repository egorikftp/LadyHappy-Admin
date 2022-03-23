package com.egoriku.config.store

import com.arkivanov.mvikotlin.core.store.Store
import com.egoriku.config.store.ConfigStore.Intent
import com.egoriku.config.store.ConfigStore.State

internal interface ConfigStore : Store<Intent, State, Nothing> {

    sealed class Intent

    sealed class Result {
        data class Loaded(val items: List<Int>) : Result()
        object Loading: Result()
    }

    data class State(
        val isLoading: Boolean = false,
        val items: List<Int> = emptyList()
    )
}