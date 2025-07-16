package com.kuit.ourmenu.data.model.map.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MapMenuDetailResponse(
    @SerialName("menuId")
    val menuId: Int,
    @SerialName("menuTitle")
    val menuTitle: String,
    @SerialName("menuPrice")
    val menuPrice: Int,
    @SerialName("menuPinImgUrl")
    val menuPinImgUrl: String,
    @SerialName("menuTagImgUrls")
    val menuTagImgUrls: List<String>,
    @SerialName("menuImgUrls")
    val menuImgUrls: List<String>,
    @SerialName("menuFolderInfo")
    val menuFolderInfo: MenuFolderInfo,
    @SerialName("mapId")
    val mapId: Int,
    @SerialName("mapX")
    val mapX: Int,
    @SerialName("mapY")
    val mapY: Int,
)



