package com.kuit.ourmenu.data.model.map.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MapDetailResponse(
    @SerialName("menuId")
    val menuId: Long,
    @SerialName("menuTitle")
    val menuTitle: String,
    @SerialName("menuPrice")
    val menuPrice: Int,
    @SerialName("storeTitle")
    val storeTitle: String,
    @SerialName("menuPinImgUrl")
    val menuPinImgUrl: String,
    @SerialName("menuTagImgUrls")
    val menuTagImgUrls: List<String>,
    @SerialName("menuImgUrls")
    val menuImgUrls: List<String>,
    @SerialName("menuFolderInfo")
    val menuFolderInfo: MenuFolderInfo,
    @SerialName("mapId")
    val mapId: Long,
    @SerialName("mapX")
    val mapX: Double,
    @SerialName("mapY")
    val mapY: Double
)

@Serializable
data class MenuFolderInfo(
    @SerialName("menuFolderTitle")
    val menuFolderTitle: String,
    @SerialName("menuFolderIconImgUrl")
    val menuFolderIconImgUrl: String,
    @SerialName("menuFolderCount")
    val menuFolderCount: Int
)
