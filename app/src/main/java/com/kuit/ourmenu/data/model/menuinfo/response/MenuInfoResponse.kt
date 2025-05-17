package com.kuit.ourmenu.data.model.menuinfo.response

import com.kuit.ourmenu.data.model.base.type.MenuFolderIconType
import com.kuit.ourmenu.data.model.base.type.TagType
import kotlinx.serialization.Serializable

@Serializable
data class MenuInfoResponse(
    val menuId: Int = 0,
    val menuTitle: String = "",
    val menuPrice: Int = 0,
    val menuPin: String = "",
    val storeTitle: String = "",
    val storeAddress: String = "",
    val tags: List<TagType> = emptyList(),
    val menuImgUrls: List<String> = emptyList(),
    val menuFolders: List<MenuFolder> = emptyList(),
)

@Serializable
data class MenuFolder(
    val menuFolderId: Int = 0,
    val menuFolderTitle: String = "",
    val menuFolderIcon: MenuFolderIconType = MenuFolderIconType.ANGRY,
)