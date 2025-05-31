package com.kuit.ourmenu.data.service

import com.kuit.ourmenu.data.model.base.BaseResponse
import com.kuit.ourmenu.data.model.menuFolder.response.MenuFolderAllResponse
import com.kuit.ourmenu.data.model.menuFolder.response.MenuFolderDetailResponse
import com.kuit.ourmenu.data.model.menuFolder.response.MenuFolderResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MenuFolderService {
    @GET("api/menu-folders")
    suspend fun getMenuFolders(): BaseResponse<MenuFolderResponse>

    @GET("api/menu-folders/{menuFolderId}/menus")
    suspend fun getMenuFolderDetails(
        @Path("menuFolderId") menuFolderId: Int,
        @Query("sortOrder") sortOrder: String,
    ): BaseResponse<MenuFolderDetailResponse>

    @GET("api/menus")
    suspend fun getMenuFolderAll(
        @Query("tags") tags: List<String>? = null,
        @Query("minPrice") minPrice: Long? = null,
        @Query("maxPrice") maxPrice: Long? = null,
        @Query("page") page: Int? = null,
        @Query("size") size: Int,
        @Query("sortOrder") sortOrder: String,
    ): BaseResponse<List<MenuFolderAllResponse>>
}