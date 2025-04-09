package com.kuit.ourmenu.data.model.menuFolder.response

import kotlinx.serialization.Serializable

@Serializable
data class MenuFolderAllResponse(
    val menuId: Int,
    val menuTitle: String,
    val storeTitle: String,
    val storeAddress: String,
    val menuPrice: Int,
    val menuImgUrl: String,
)
