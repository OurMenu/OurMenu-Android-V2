package com.kuit.ourmenu.data.model.menuinfo.response

import kotlinx.serialization.Serializable

@Serializable
data class MenuInfoResponse(
    val menuId: Long = 0,
    val menuTitle: String = "",
    val menuPrice: Int = 0,
    val menuPinImgUrl: String = "",
    val menuMemoTitle: String = "",
    val menuMemoContent: String = "",
    val storeTitle: String = "",
    val storeAddress: String = "",
    val tagImgUrls: List<String> = emptyList(),
    val menuImgUrls: List<String> = emptyList(),
    val menuFolders: List<MenuFolder> = emptyList(),
)

@Serializable
data class MenuFolder(
    val menuFolderId: Int = 0,
    val menuFolderTitle: String = "",
    val menuFolderIconImgUrl: String = "",
)