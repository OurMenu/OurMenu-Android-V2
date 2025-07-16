package com.kuit.ourmenu.data.model.map.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MapResponse(
    @SerialName("mapId")
    val mapId: Long,
    @SerialName("menuPinImgUrl")
    val menuPinImgUrl: String,
    @SerialName("menuPinDisabledImgUrl")
    val menuPinDisabledImgUrl: String,
    @SerialName("mapX")
    val mapX: Double,
    @SerialName("mapY")
    val mapY: Double
)
