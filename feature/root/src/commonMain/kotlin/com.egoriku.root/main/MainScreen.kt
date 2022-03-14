package com.egoriku.root.main

interface MainScreen {

    fun onFeatureClick(features: MainFeatures)
}

sealed class MainFeatures {
    object Categories : MainFeatures()
}