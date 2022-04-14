package com.egoriku.features.domain

data class FeaturesItem(
    val featureId: Int,
    val sheetName: String,
    val iconUrl: String,
    val isAvailable: Boolean
)