package com.egoriku.config.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Config(
    @SerialName("category_id")
    val categoryId: Int,

    @SerialName("category_name")
    val categoryName: String,

    @SerialName("subcategory_id")
    val subCategoryId: Int,

    @SerialName("subcategory_name")
    val subCategoryName: String,

    @SerialName("description")
    val description: String
)