package com.egoriku.features.data

import com.egoriku.retrosheetkmm.serializer.RetroBooleanSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Features(
    @SerialName("feature_id")
    val featureId: Int,

    @SerialName("sheet_name")
    val sheetName: String,

    @SerialName("icon_url")
    val iconUrl: String,

    @SerialName("is_available")
    @Serializable(with = RetroBooleanSerializer::class)
    val isAvailable: Boolean
)
