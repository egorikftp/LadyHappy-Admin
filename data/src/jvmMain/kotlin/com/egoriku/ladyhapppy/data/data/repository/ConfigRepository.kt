package com.egoriku.ladyhapppy.data.data.repository

import com.egoriku.ladyhapppy.data.data.api.ApiInterface

class ConfigRepository(private val apiInterface: ApiInterface) {

    fun getConfig() = apiInterface.getConfig()
}