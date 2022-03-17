package com.egoriku.features

class FeaturesComponentImpl(private val onClick: () -> Unit) : FeaturesComponent {

    override fun onFeatureClick() {
        onClick()
    }
}