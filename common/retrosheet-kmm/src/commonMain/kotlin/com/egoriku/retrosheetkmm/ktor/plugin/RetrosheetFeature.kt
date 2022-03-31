package com.egoriku.retrosheetkmm.ktor.plugin

import com.egoriku.retrosheetkmm.ktor.iGoogleSheetUrl
import com.egoriku.retrosheetkmm.ktor.replace
import com.egoriku.retrosheetkmm.parseCsv
import com.egoriku.retrosheetkmm.util.QueryConverter
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*
import io.ktor.util.reflect.*
import io.ktor.utils.io.*
import io.ktor.utils.io.core.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

class RetrosheetFeature(
    private val serializer: JsonSerializer,
    private val sheets: Map<String, Map<String, String>>,
) {

    class Config {
        var serializer: JsonSerializer? = null
        var configuration: Configuration = Configuration(sheets = emptyMap())
    }

    companion object Feature : HttpClientFeature<Config, RetrosheetFeature> {

        override val key: AttributeKey<RetrosheetFeature> = AttributeKey("retrosheet-kmm")

        override fun prepare(block: Config.() -> Unit): RetrosheetFeature {
            val config = Config().apply(block)

            return RetrosheetFeature(
                serializer = config.serializer ?: defaultSerializer(),
                sheets = config.configuration.sheets
            )
        }

        override fun install(feature: RetrosheetFeature, scope: HttpClient) {
            scope.requestPipeline.intercept(HttpRequestPipeline.Before) {
                val urlBuilder = context.url

                when {
                    urlBuilder.iGoogleSheetUrl -> {
                        context.url {
                            encodedPath = encodedPath.plus("/gviz/tq")

                            parameters.append("tqx", "out:csv")

                            val sheetName = requireNotNull(urlBuilder.parameters["sheet"])

                            val query = urlBuilder.parameters["tq"]

                            if (!query.isNullOrEmpty()) {
                                val escapedQuery = QueryConverter(
                                    smartQuery = query,
                                    smartQueryMap = requireNotNull(feature.sheets[sheetName]) {
                                        "Couldn't find queryMap for '$sheetName'"
                                    },
                                ).convert()

                                parameters.replace("tq", escapedQuery)
                            }
                        }

                        println(context.url.buildString())
                        proceedWith(it)
                    }
                    else -> return@intercept
                }
            }

            scope.responsePipeline.intercept(HttpResponsePipeline.Receive) { (info: TypeInfo, body: Any) ->
                if (!context.request.url.iGoogleSheetUrl()) return@intercept
                if (body !is ByteReadChannel) return@intercept

                val csvString = parseCsv(data = body.readRemaining().readText())

                val encodeToString = Json.encodeToString(
                    serializer = ListSerializer(
                        elementSerializer = MapSerializer(
                            String.serializer(),
                            String.serializer()
                        )
                    ),
                    value = csvString
                ).encodeToByteArray()

                val response = HttpResponseContainer(
                    expectedType = info,
                    response = feature.serializer.read(info, ByteReadPacket(encodeToString))
                )
                proceedWith(response)
            }
        }
    }
}
