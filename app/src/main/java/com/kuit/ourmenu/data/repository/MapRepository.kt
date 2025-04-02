package com.kuit.ourmenu.data.repository

import com.kuit.ourmenu.data.model.base.handleBaseResponse
import com.kuit.ourmenu.data.service.MapService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapRepository @Inject constructor(
    private val mapService: MapService
) {
    suspend fun getMapDetail(
        mapId: Int
    ) = runCatching {
        val response = mapService.getMapDetail(
            mapId = mapId
        ).handleBaseResponse().getOrThrow()
    }

    suspend fun getMap() = runCatching {
        val response = mapService.getMap().handleBaseResponse().getOrThrow()
    }

    suspend fun getMapMenuDetail(
        menuId: Int
    ) = runCatching {
        val response = mapService.getMapMenuDetail(
            menuId = menuId
        ).handleBaseResponse().getOrThrow()
    }

    suspend fun getMapSearch(
        title: String,
        mapX: Double?,
        mapY: Double?
    ) = runCatching {
        val response = mapService.getMapSearch(
            title = title,
            mapX = mapX,
            mapY = mapY
        ).handleBaseResponse().getOrThrow()
    }

    suspend fun getMapSearchHistory() = runCatching {
        val response = mapService.getMapSearchHistory().handleBaseResponse().getOrThrow()
    }

    // 크롤링 관련
    suspend fun getCrawlingHistory() = runCatching {
        val response = mapService.getCrawlingHistory().handleBaseResponse().getOrThrow()
    }

    suspend fun getCrawlingStoreDetail(
        isCrawled: Boolean,
        storeId: String
    ) = runCatching {
        val response = mapService.getCrawlingStoreDetail(
            isCrawled = isCrawled,
            storeId = storeId
        ).handleBaseResponse().getOrThrow()
    }

    suspend fun getCrawlingStoreInfo(
        query: String,
        mapX: Double,
        mapY: Double
    ) = kotlin.runCatching {
        val response = mapService.getCrawlingStoreInfo(
            query = query,
            mapX = mapX,
            mapY = mapY
        ).handleBaseResponse().getOrThrow()
    }

}