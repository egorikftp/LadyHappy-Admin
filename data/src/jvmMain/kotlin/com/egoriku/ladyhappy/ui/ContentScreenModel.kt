package com.egoriku.ladyhappy.ui

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.egoriku.ladyhapppy.data.data.model.Config
import com.egoriku.ladyhapppy.data.data.repository.ConfigRepository
import com.egoriku.ladyhapppy.data.data.util.calladapter.flow.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ContentScreenModel(private val configRepository: ConfigRepository) : ScreenModel {

    private var _config = MutableStateFlow<Resource<List<Config>>>(Resource.Loading())
    val config = _config.asStateFlow()

    init {
        sync()
    }

    private fun sync() {
        coroutineScope.launch {
            configRepository.getConfig().collect {
                _config.emit(it)
            }
        }
    }
}