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
        mapId: Long
    ) = runCatching {
        mapService.getMapDetail(
            mapId = mapId
        ).handleBaseResponse().getOrThrow()
    }

    suspend fun getMap() = runCatching {
        mapService.getMap().handleBaseResponse().getOrThrow()
    }

    suspend fun getMapMenuDetail(
        menuId: Long
    ) = runCatching {
        mapService.getMapMenuDetail(
            menuId = menuId
        ).handleBaseResponse().getOrThrow()
    }

    suspend fun getMapSearch(
        title: String,
        longitude: Double?,
        latitude: Double?
    ) = runCatching {
        mapService.getMapSearch(
            title = title,
            longitude = longitude,
            latitude = latitude
        ).handleBaseResponse().getOrThrow()
    }

    suspend fun getMapSearchHistory() = runCatching {
        mapService.getMapSearchHistory().handleBaseResponse().getOrThrow()
    }

    // 크롤링 관련
    suspend fun getCrawlingHistory() = runCatching {
        mapService.getCrawlingHistory().handleBaseResponse().getOrThrow()
    }

    suspend fun getCrawlingStoreDetail(
        isCrawled: Boolean,
        storeId: String
    ) = runCatching {
        mapService.getCrawlingStoreDetail(
            isCrawled = isCrawled,
            storeId = storeId
        ).handleBaseResponse().getOrThrow()
    }

    suspend fun getCrawlingStoreInfo(
        query: String,
        longitude: Double,
        latitude: Double
    ) = kotlin.runCatching {
        mapService.getCrawlingStoreInfo(
            query = query,
            longitude = longitude,
            latitude = latitude
        ).handleBaseResponse().getOrThrow()
    }

}