package com.egoriku.features.data.repository

import com.egoriku.features.data.Features
import com.egoriku.network.Sheets.FEATURES
import com.egoriku.retrosheetkmm.ktor.sheet
import io.ktor.client.*
import io.ktor.client.request.*

class FeaturesRepository(private val httpClient: HttpClient) {

    suspend fun load(): List<Features> {
        return httpClient.get {
            sheet(FEATURES)
        }
    }
}