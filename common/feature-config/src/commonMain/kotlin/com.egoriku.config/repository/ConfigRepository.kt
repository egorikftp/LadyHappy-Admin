package com.egoriku.config.repository

import com.egoriku.config.domain.Config
import com.egoriku.network.Sheets.CATEGORIES
import com.egoriku.retrosheetkmm.ktor.query
import com.egoriku.retrosheetkmm.ktor.sheet
import io.ktor.client.*
import io.ktor.client.request.*

class ConfigRepository(private val httpClient: HttpClient) {

    suspend fun load(): List<Config> {
        return httpClient.get {
            sheet(CATEGORIES)
        }
    }

    suspend fun load(category: Int): List<Config> {
        return httpClient.get {
            sheet("categories")
            query("SELECT * WHERE category_id = 1")
        }
    }
}