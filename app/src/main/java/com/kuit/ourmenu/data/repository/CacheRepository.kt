package com.kuit.ourmenu.data.repository

import com.kuit.ourmenu.data.model.base.handleBaseResponse
import com.kuit.ourmenu.data.service.CacheService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CacheRepository @Inject constructor(
    private val cacheService: CacheService
) {
    suspend fun getCacheData() = runCatching {
        cacheService.getCacheData().handleBaseResponse().getOrThrow()
    }
}