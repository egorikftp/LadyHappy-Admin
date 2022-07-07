package com.egoriku.config.repository

import com.egoriku.config.domain.Config
import com.egoriku.ktor.retrosheet.ktor.query
import com.egoriku.ktor.retrosheet.ktor.sheet
import com.egoriku.network.Sheets.CATEGORIES
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class ConfigRepository(private val httpClient: HttpClient) {

    suspend fun load(): List<Config> {
        return httpClient.get {
            sheet(CATEGORIES)
        }.body()
    }

    suspend fun load(category: Int): List<Config> {
        return httpClient.get {
            sheet("categories")
            query("SELECT * WHERE category_id = 1")
        }.body()
    }
}