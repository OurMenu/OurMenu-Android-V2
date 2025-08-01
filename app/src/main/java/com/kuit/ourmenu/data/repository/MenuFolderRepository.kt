package com.kuit.ourmenu.data.repository

import com.kuit.ourmenu.data.model.base.handleBaseResponse
import com.kuit.ourmenu.data.service.MenuFolderService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MenuFolderRepository @Inject constructor(
    private val menuFolderService: MenuFolderService,
) {
    suspend fun getMenuFolders() = runCatching {
        menuFolderService.getMenuFolders().handleBaseResponse().getOrThrow()
    }

    suspend fun getMenuFolderDetail(
        menuFolderId: Int,
        sortOrder: String,
    ) = runCatching {
        menuFolderService.getMenuFolderDetails(
            menuFolderId = menuFolderId,
            sortOrder = sortOrder
        ).handleBaseResponse().getOrThrow()
    }

    suspend fun getMenuFolderAll(
        tags: List<String>? = null,
        minPrice: Long? = null,
        maxPrice: Long? = null,
        page: Int? = null,
        size: Int = 10,
        sortOrder: String,
    ) = runCatching {
        menuFolderService.getMenuFolderAll(
            tags = tags,
            minPrice = minPrice,
            maxPrice = maxPrice,
            page = page,
            size = size,
            sortOrder = sortOrder
        ).handleBaseResponse().getOrThrow()
    }

    suspend fun deleteMenuFolder(
        menuFolderId: Long
    ) = runCatching {
        menuFolderService.deleteMenuFolder(menuFolderId).handleBaseResponse().getOrThrow()
    }
}