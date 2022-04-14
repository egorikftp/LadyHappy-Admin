package com.egoriku.features

import com.egoriku.features.domain.FeaturesItem
import kotlinx.coroutines.flow.Flow

interface FeaturesComponent {

    val model: Flow<Model>

    fun onFeatureClick()

    data class Model(
        val features: List<FeaturesItem> = emptyList(),
        val isLoading: Boolean = false
    )
}