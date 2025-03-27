package com.kuit.ourmenu.data.model.map.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MapResponse(
    @SerialName("mapId")
    val mapId: Int,
    @SerialName("menuPins")
    val menuPins: List<String>,
    @SerialName("mapX")
    val mapX: Int,
    @SerialName("mapY")
    val mapY: Int
)
