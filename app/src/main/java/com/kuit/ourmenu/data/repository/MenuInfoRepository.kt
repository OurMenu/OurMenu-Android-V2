package com.kuit.ourmenu.data.repository

import com.kuit.ourmenu.data.model.base.handleBaseResponse
import com.kuit.ourmenu.data.service.MenuInfoService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MenuInfoRepository @Inject constructor(
    private val menuInfoService: MenuInfoService
) {
    suspend fun getMenuInfo(
        menuId: Int
    ) = runCatching {
        menuInfoService.getMenuInfo(menuId).handleBaseResponse().getOrThrow()
    }
}