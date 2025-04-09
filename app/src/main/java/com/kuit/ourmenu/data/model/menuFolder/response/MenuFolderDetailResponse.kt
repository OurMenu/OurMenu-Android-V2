package com.kuit.ourmenu.data.model.menuFolder.response

import kotlinx.serialization.Serializable

@Serializable
data class MenuFolderDetailResponse(
    val menuId: Int,
    val menuTitle: String,
    val storeTitle: String,
    val storeAddress: String,
    val menuPrice: Int,
    val menuImgUrl: String,
    val createdAt: String,
)