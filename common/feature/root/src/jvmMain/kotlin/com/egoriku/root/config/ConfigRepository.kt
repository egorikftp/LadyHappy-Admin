package com.egoriku.root.config

import com.egoriku.ladyhapppy.network.ApiInterface

class ConfigRepository(private val apiInterface: ApiInterface) {

    fun getConfig() = apiInterface.getConfig()
}

