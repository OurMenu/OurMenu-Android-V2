package com.kuit.ourmenu.data.model.map.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MapSearchHistoryResponse(
    @SerialName("mapId")
    val mapId: Long,
    @SerialName("menuId")
    val menuId: Long,
    @SerialName("menuTitle")
    val menuTitle: String,
    @SerialName("storeTitle")
    val storeTitle: String,
    @SerialName("storeAddress")
    val storeAddress: String
)
