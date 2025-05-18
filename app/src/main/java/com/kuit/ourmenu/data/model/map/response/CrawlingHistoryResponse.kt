package com.kuit.ourmenu.data.model.map.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CrawlingHistoryResponse(
    @SerialName("menuTitle")
    val menuTitle: String,
    @SerialName("storeAddress")
    val storeAddress: String,
    @SerialName("modifiedAt")
    val modifiedAt: String
)
