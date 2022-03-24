package com.egoriku.config.repository

import com.egoriku.config.domain.Config

expect class ConfigRepository {

    suspend fun loadConfig(): List<Config>
}