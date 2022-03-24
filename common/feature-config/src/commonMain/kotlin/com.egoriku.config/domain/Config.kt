package com.egoriku.config.domain

data class Config(
    val categoryId: Int,
    val categoryName: String,
    val subCategoryId: Int,
    val subCategoryName: String,
    val description: String
)