package com.egoriku.config

import com.egoriku.config.domain.Config
import kotlinx.coroutines.flow.Flow

interface ConfigComponent {

    val model: Flow<Model>

    fun onCloseClicked()

    data class Model(
        val isLoading: Boolean = false,
        val config: List<Config> = emptyList()
    )
}