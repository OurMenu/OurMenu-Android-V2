package com.kuit.ourmenu.data.service

import com.kuit.ourmenu.data.model.base.BaseResponse
import com.kuit.ourmenu.data.model.menuFolder.response.MenuFolderResponse
import retrofit2.http.GET

interface MenuFolderService {
    @GET("api/menu-folders")
    suspend fun getMenuFolders(): BaseResponse<MenuFolderResponse>
}