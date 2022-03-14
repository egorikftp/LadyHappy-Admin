package com.egoriku.root.config

import com.egoriku.ladyhapppy.Resource
import com.egoriku.ladyhapppy.network.model.Config
import kotlinx.coroutines.flow.Flow

interface IConfigRepository {
    fun getConfig(): Flow<Resource<List<Config>>>
}