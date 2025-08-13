package com.kuit.ourmenu.data.model.menuFolder.response

import kotlinx.serialization.Serializable

@Serializable
data class MenuFolderAllResponse(
    override val menuId: Long,
    override val menuTitle: String,
    override val storeTitle: String,
    override val storeAddress: String,
    override val menuPrice: Int,
    override val menuImgUrl: String
) : MenuFolderMenuItem
