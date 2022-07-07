package com.egoriku.features.domain

data class FeaturesItem(
    val featureName: String,
    val sheetName: String,
    val iconUrl: String,
    val isAvailable: Boolean
)