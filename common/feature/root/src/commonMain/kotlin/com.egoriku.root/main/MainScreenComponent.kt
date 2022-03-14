package com.egoriku.root.main

class MainScreenComponent(
    private val onClick: (MainFeatures) -> Unit
) : MainScreen {

    override fun onFeatureClick(features: MainFeatures) = onClick(features)
}