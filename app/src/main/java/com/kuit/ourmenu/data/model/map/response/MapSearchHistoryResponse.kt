package com.kuit.ourmenu.data.model.map.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MapSearchHistoryResponse(
    @SerialName("menuId")
    val menuId: Int,
    @SerialName("menuTitle")
    val menuTitle: String,
    @SerialName("storeTitle")
    val storeTitle: String,
    @SerialName("storeAddress")
    val storeAddress: String
)
