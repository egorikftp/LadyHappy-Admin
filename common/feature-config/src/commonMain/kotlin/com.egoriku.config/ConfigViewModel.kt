package com.egoriku.config

import com.egoriku.viewmodel.BaseViewModel
import kotlinx.coroutines.delay

sealed class ConfigResult {
    object Loading : ConfigResult()
    object Error : ConfigResult()
    data class Success(val data: List<Int>) : ConfigResult()
}

class ConfigViewModel : BaseViewModel<ConfigResult>() {

    override val loadingResult: ConfigResult
        get() = ConfigResult.Loading

    override fun errorResult(throwable: Throwable): ConfigResult {
        return ConfigResult.Error
    }

    init {
        getConfig()
    }

    private fun getConfig() = launch {
        delay(1000)

        ConfigResult.Success(List(100) { it + 1 })
    }
}