package com.kuit.ourmenu.data.model.menuFolder.response

interface MenuFolderMenuItem {
    val menuId: Long
    val menuTitle: String
    val storeTitle: String
    val storeAddress: String
    val menuPrice: Int
    val menuImgUrl: String
}