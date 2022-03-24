package com.egoriku.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Config(
    @Json(name = "category_id")
    val categoryId: Int,

    @Json(name = "category_name")
    val categoryName: String,

    @Json(name = "subcategory_id")
    val subCategoryId: Int,

    @Json(name = "subcategory_name")
    val subCategoryName: String,

    @Json(name = "description")
    val description: String,
)