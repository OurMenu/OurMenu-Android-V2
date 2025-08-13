package com.kuit.ourmenu.data.service

import com.kuit.ourmenu.data.model.base.BaseResponse
import com.kuit.ourmenu.data.model.cache.response.CacheInfoResponse
import retrofit2.http.GET

interface CacheService {
    @GET("api/cache-data")
    suspend fun getCacheData() : BaseResponse<CacheInfoResponse>
}