package com.egoriku.config

import kotlinx.coroutines.flow.Flow

interface ConfigComponent {

    val model: Flow<Model>

    fun onCloseClicked()

    data class Model(
        val isLoading: Boolean = false,
        val categories: List<Int> = emptyList()
    )
}