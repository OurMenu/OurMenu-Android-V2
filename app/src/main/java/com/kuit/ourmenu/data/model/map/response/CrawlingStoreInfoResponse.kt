package com.kuit.ourmenu.data.model.map.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CrawlingStoreInfoResponse(
    @SerialName("storeTitle")
    val storeTitle: String,

    @SerialName("storeAddress")
    val storeAddress: String,

    @SerialName("storeId")
    val storeId: String,

    @SerialName("crawled")
    val crawled: Boolean
)
