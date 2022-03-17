package com.egoriku.config

internal class ConfigComponentImpl(
    private val onBack: () -> Unit
) : ConfigComponent {

    override fun onCloseClicked() = onBack()
}