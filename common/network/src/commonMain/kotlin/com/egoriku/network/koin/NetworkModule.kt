package com.egoriku.network.koin

import com.egoriku.ktor.retrosheet.ktor.defaultSheetUrl
import com.egoriku.ktor.retrosheet.ktor.plugin.RetrosheetPlugin
import com.egoriku.ktor.retrosheet.ktor.plugin.configuration
import com.egoriku.network.Sheets.CATEGORIES
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }

            defaultRequest {
                defaultSheetUrl(docId = "1XrYNW2hX4lMxMhd8rOFzk5vrjYlxg3WxEKPAqDMhB54")
            }
            install(RetrosheetPlugin) {
                configuration {
                    sheet {
                        name = CATEGORIES
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
        }
    }
}


