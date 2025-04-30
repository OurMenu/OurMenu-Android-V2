package com.kuit.ourmenu.data.model.menuFolder.response

import kotlinx.serialization.Serializable

@Serializable
data class MenuFolderResponse(
    val menuCount: Int,
    val menuFolders: List<MenuFolderList>
)

@Serializable
data class MenuFolderList(
    val menuFolderId: Int,
    val menuFolderTitle: String,
    val menuFolderUrl: String,
    val menuFolderIcon: String,
    val menuIds: List<Int>,
    val index: Int,
)