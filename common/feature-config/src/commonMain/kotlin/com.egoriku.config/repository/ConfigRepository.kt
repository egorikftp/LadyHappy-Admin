package com.egoriku.config.repository

import com.egoriku.config.domain.Config
import com.egoriku.retrosheetkmm.ktor.defaultSheetUrl
import com.egoriku.retrosheetkmm.ktor.plugin.RetrosheetFeature
import com.egoriku.retrosheetkmm.ktor.plugin.configuration
import com.egoriku.retrosheetkmm.ktor.query
import com.egoriku.retrosheetkmm.ktor.sheet
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class ConfigRepository {

    private val client = HttpClient(CIO) {
        defaultRequest {
            defaultSheetUrl(docId = "1XrYNW2hX4lMxMhd8rOFzk5vrjYlxg3WxEKPAqDMhB54")
        }
        install(RetrosheetFeature) {
            configuration {
                sheet {
                    name = "categories"
                    columns = listOf(
                        "category_id",
                        "category_name",
                        "subcategory_id",
                        "subcategory_name",
                        "description"
                    )
                }
            }
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun load(): List<Config> {
        return client.get {
            sheet("categories")
        }
    }

    suspend fun load(category: Int): List<Config> {
        return client.get {
            sheet("categories")
            query("SELECT * WHERE category_id = 1")
        }
    }
}