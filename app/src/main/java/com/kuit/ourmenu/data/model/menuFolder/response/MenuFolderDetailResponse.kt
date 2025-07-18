package com.kuit.ourmenu.data.model.menuFolder.response

import kotlinx.serialization.Serializable

@Serializable
data class MenuFolderDetailResponse(
    val menuFolderId: Int = 0,
    val menuFolderTitle: String = "",
    val menuFolderImgUrl: String = "",
    val menuFolderIconImgUrl: String = "",
    val menus: List<MenuFolderDetailMenus> = emptyList()
)

@Serializable
data class MenuFolderDetailMenus(
    override val menuId: Int = 0,
    override val menuTitle: String = "",
    override val storeTitle: String = "",
    override val storeAddress: String = "",
    override val menuPrice: Int = 0,
    override val menuImgUrl: String = ""
) : MenuFolderMenuItem