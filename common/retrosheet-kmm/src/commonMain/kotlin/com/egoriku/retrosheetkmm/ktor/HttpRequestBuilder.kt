package com.egoriku.retrosheetkmm.ktor

import io.ktor.client.request.*
import io.ktor.http.*

fun HttpRequestBuilder.sheet(value: Any?) = parameter("sheet", value)

fun HttpRequestBuilder.query(value: Any?) = parameter("tq", value)

fun HttpRequestBuilder.defaultSheetUrl(docId: String) {
    url {
        protocol = URLProtocol.HTTPS
        host = "docs.google.com"
        encodedPath = "/spreadsheets/d/$docId"
    }
}