package com.kuit.ourmenu.data.service

import com.kuit.ourmenu.data.model.base.BaseResponse
import com.kuit.ourmenu.data.model.map.response.CrawlingHistoryResponse
import com.kuit.ourmenu.data.model.map.response.CrawlingStoreDetailResponse
import com.kuit.ourmenu.data.model.map.response.CrawlingStoreInfoResponse
import com.kuit.ourmenu.data.model.map.response.MapDetailResponse
import com.kuit.ourmenu.data.model.map.response.MapMenuDetailResponse
import com.kuit.ourmenu.data.model.map.response.MapResponse
import com.kuit.ourmenu.data.model.map.response.MapSearchHistoryResponse
import com.kuit.ourmenu.data.model.map.response.MapSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MapService {

    @GET("api/users/menus/{mapId}/maps")
    suspend fun getMapDetail(
        @Path("mapId") mapId: Long
    ): BaseResponse<List<MapDetailResponse>> // TODO: 리팩토링

    @GET("api/users/menus/maps")
    suspend fun getMap(): BaseResponse<List<MapResponse>> // TODO: 리팩토링

    @GET("api/users/menus/maps/{menuId}/search")
    suspend fun getMapMenuDetail(
        @Path("menuId") menuId: Long
    ): BaseResponse<MapMenuDetailResponse> // TODO: 리팩토링

    @GET("api/users/menus/maps/search")
    suspend fun getMapSearch(
        @Query("title") title: String,
        @Query("mapX") longitude: Double?,
        @Query("mapY") latitude: Double?
    ): BaseResponse<List<MapSearchResponse>>

    @GET("api/users/menus/maps/search-history")
    suspend fun getMapSearchHistory(): BaseResponse<List<MapSearchHistoryResponse>>

    // 크롤링 관련 요청
    @GET("api/priored/users/{userId}/histories")
    suspend fun getCrawlingHistory(): BaseResponse<List<CrawlingHistoryResponse>>

    @GET("api/priored/stores/{storeId}")
    suspend fun getCrawlingStoreDetail(
        @Path("storeId") storeId: String,
        @Query("is-crawled") isCrawled: Boolean
    ): BaseResponse<CrawlingStoreDetailResponse>

    @GET("api/priored/stores/menus")
    suspend fun getCrawlingStoreInfo(
        @Query("query") query: String,
        @Query("mapX") longitude: Double,
        @Query("mapY") latitude: Double
    ): BaseResponse<List<CrawlingStoreInfoResponse>>
}