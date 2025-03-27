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

}