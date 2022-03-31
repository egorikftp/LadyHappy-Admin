package com.egoriku.network.koin

import com.egoriku.network.Sheets.CATEGORIES
import com.egoriku.retrosheetkmm.ktor.defaultSheetUrl
import com.egoriku.retrosheetkmm.ktor.plugin.RetrosheetFeature
import com.egoriku.retrosheetkmm.ktor.plugin.configuration
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: Module = module {
    single {
        HttpClient(CIO) {
            defaultRequest {
                defaultSheetUrl(docId = "1XrYNW2hX4lMxMhd8rOFzk5vrjYlxg3WxEKPAqDMhB54")
            }
            install(RetrosheetFeature) {
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

            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }
}


