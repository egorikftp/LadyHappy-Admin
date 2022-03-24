package com.egoriku.config.repository

import com.egoriku.config.domain.Config
import com.egoriku.network.ApiInterface
import com.egoriku.utils.AppCoroutineDispatcher
import kotlinx.coroutines.withContext

actual class ConfigRepository(private val api: ApiInterface) {

    actual suspend fun loadConfig() = withContext(AppCoroutineDispatcher.IO) {
        api.getConfig().map { config ->
            Config(
                categoryId = config.categoryId,
                categoryName = config.categoryName,
                subCategoryId = config.subCategoryId,
                subCategoryName = config.subCategoryName,
                description = config.description
            )
        }
    }
}