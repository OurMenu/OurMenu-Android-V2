package com.kuit.ourmenu.data.model.menuFolder.request

import kotlinx.serialization.Serializable

@Serializable
data class MenuFolderIndexRequest(
    val index: Int,
)