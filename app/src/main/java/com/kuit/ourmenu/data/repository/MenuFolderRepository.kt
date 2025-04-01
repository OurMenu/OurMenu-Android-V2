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
}