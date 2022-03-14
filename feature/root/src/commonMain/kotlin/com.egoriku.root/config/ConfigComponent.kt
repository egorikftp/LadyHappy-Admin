package com.egoriku.root.config

class ConfigComponent(
    private val onClose: () -> Unit
) : ConfigScreen {

    override fun onCloseClicked() = onClose()
}