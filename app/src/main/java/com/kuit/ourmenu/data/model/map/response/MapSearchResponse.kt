package com.kuit.ourmenu.data.model.map.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MapSearchResponse(
    @SerialName("menuTitle")
    val menuTitle: String,
    @SerialName("storeTitle")
    val storeTitle: String,
    @SerialName("storeAddress")
    val storeAddress: String
)
