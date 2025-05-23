package com.kuit.ourmenu.data.service

import com.kuit.ourmenu.data.model.base.BaseResponse
import com.kuit.ourmenu.data.model.home.response.HomeResponse
import retrofit2.http.GET

interface HomeService {
    @GET("api/home")
    suspend fun getHome(): BaseResponse<HomeResponse>
}