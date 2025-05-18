package com.kuit.ourmenu.data.model.map.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CrawlingStoreDetailResponse(
    @SerialName("storeId")
    val storeId: String,
    @SerialName("storeTitle")
    val storeTitle: String,
    @SerialName("storeAddress")
    val storeAddress: String,
    @SerialName("storeImgs")
    val storeImgs: List<String>,
    @SerialName("menus")
    val menus: List<CrawlingMenuDetail>,
    @SerialName("storeMapX")
    val storeMapX: Double,
    @SerialName("storeMapY")
    val storeMapY: Double,
)

@Serializable
data class CrawlingMenuDetail(
    @SerialName("menuTitle")
    val menuTitle: String,
    @SerialName("menuPrice")
    val menuPrice: String
)
