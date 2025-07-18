package com.kuit.ourmenu.data.service

import com.kuit.ourmenu.data.model.base.BaseResponse
import com.kuit.ourmenu.data.model.menuinfo.response.MenuInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MenuInfoService {
    @GET("api/menus/{menuId}")
    suspend fun getMenuInfo(
        @Path("menuId") menuId: Int
    ): BaseResponse<MenuInfoResponse>
}
