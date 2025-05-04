package com.kuit.ourmenu.ui.searchmenu.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import com.kuit.ourmenu.R
import com.kuit.ourmenu.data.model.map.response.CrawlingHistoryResponse
import com.kuit.ourmenu.data.repository.MapRepository
import com.kuit.ourmenu.ui.common.map.MapController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMenuViewModel @Inject constructor(
    private val mapRepository: MapRepository
) : ViewModel() {
    private val _searchHistory = MutableStateFlow<List<CrawlingHistoryResponse>?>(emptyList())
    val searchHistory = _searchHistory.asStateFlow()
    
    val mapController = MapController()
    
    // Current map center coordinates
    private val _currentCenter = MutableStateFlow<LatLng?>(null)
    val currentCenter: StateFlow<LatLng?> = _currentCenter

    // Map operations
    fun initializeMap(kakaoMap: KakaoMap) {
        // Initial map setup
        moveCamera(37.5416, 127.0793)
        addMarker(37.5406, 127.0763, R.drawable.img_popup_dice)
    }
    
    fun moveCamera(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            mapController.kakaoMap.value?.let { map ->
                val cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(latitude, longitude))
                map.moveCamera(cameraUpdate)
                updateCurrentCenter()
            }
        }
    }
    
    // Get the current center coordinates of the map
    fun updateCurrentCenter() {
        viewModelScope.launch {
            mapController.kakaoMap.value?.let { map ->
                val center = map.cameraPosition?.position
                _currentCenter.value = center
                if (center != null) {
                    Log.d("SearchMenuViewModel", "현재 지도 중심 좌표: ${center.latitude}, ${center.longitude}")
                }
            }
        }
    }
    
    // Get the current center coordinates as a Pair
    fun getCurrentCoordinates(): Pair<Double, Double>? {
        return currentCenter.value?.let { 
            Pair(it.latitude, it.longitude) 
        }
    }
    
    fun addMarker(latitude: Double, longitude: Double, resourceId: Int) {
        viewModelScope.launch {
            mapController.kakaoMap.value?.let { map ->
                val style = map.labelManager?.addLabelStyles(
                    LabelStyles.from(LabelStyle.from(resourceId))
                )
                val options = LabelOptions.from(LatLng.from(latitude, longitude)).setStyles(style)
                map.labelManager?.layer?.addLabel(options)
            }
        }
    }
    
    fun clearMarkers() {
        viewModelScope.launch {
            mapController.kakaoMap.value?.let { map ->
                map.labelManager?.layer?.removeAll()
            }
        }
    }

    fun getCrawlingHistory() {
        viewModelScope.launch {
            val response = mapRepository.getCrawlingHistory()
            response.onSuccess {
                _searchHistory.value = it
                Log.d("SearchMenuViewModel", "크롤링 기록 조회 성공")
            }.onFailure {
                // Handle error
                Log.d("SearchMenuViewModel", "크롤링 기록 조회 실패 : ${it.message}")
            }
        }
    }

    suspend fun getCrawlingStoreDetail(
        isCrawled: Boolean,
        storeId: String
    ) {
        val response = mapRepository.getCrawlingStoreDetail(
            isCrawled = isCrawled,
            storeId = storeId
        )

    }

    fun getCrawlingStoreInfo(
        query: String,
        mapX: Double,
        mapY: Double
    ) {
        viewModelScope.launch {
            Log.d("SearchMenuViewModel", "크롤링 스토어 정보 요청: $query, 좌표($mapY, $mapX)")
            
            val response = mapRepository.getCrawlingStoreInfo(
                query = query,
                mapX = mapX,
                mapY = mapY
            )
            
            response.onSuccess { result ->
                if (result != null) {
                    Log.d("SearchMenuViewModel", "크롤링 스토어 정보 조회 성공: ${result.storeTitle}")
                }
                
                // 마커 초기화
                clearMarkers()
                
                // 마커 추가
                addMarker(mapY, mapX, R.drawable.img_popup_dice)
                
                // 카메라 이동 (필요한 경우)
                // moveCamera(mapY, mapX)
            }.onFailure {
                Log.d("SearchMenuViewModel", "크롤링 스토어 정보 조회 실패: ${it.message}")
            }
        }
    }
}