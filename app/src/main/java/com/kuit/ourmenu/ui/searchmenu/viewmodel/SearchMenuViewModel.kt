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
import com.kuit.ourmenu.data.model.map.response.MapDetailResponse
import com.kuit.ourmenu.data.model.map.response.MapResponse
import com.kuit.ourmenu.data.model.map.response.MapSearchHistoryResponse
import com.kuit.ourmenu.data.model.map.response.MapSearchResponse
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
    // 검색 기록
    private val _searchHistory = MutableStateFlow<List<MapSearchHistoryResponse>?>(emptyList())
    val searchHistory = _searchHistory.asStateFlow()

    // 전체 등록된 메뉴
    private val _myMenus = MutableStateFlow<List<MapResponse>?>(emptyList())
    val myMenus = _myMenus.asStateFlow()

    // 검색 결과
    private val _searchResult = MutableStateFlow<List<MapSearchResponse>?>(emptyList())
    val searchResult = _searchResult.asStateFlow()

    // 위치에 따른 메뉴 조회
    private val _menusOnPin = MutableStateFlow<List<MapDetailResponse>?>(emptyList())
    val menusOnPin = _menusOnPin.asStateFlow()

    // 화면에 보이는 지도 상의 중심에 해당하는 좌표
    private val _currentCenter = MutableStateFlow<LatLng?>(null)
    val currentCenter: StateFlow<LatLng?> = _currentCenter

    val mapController = MapController()

    // 지도 초기화
    fun initializeMap(kakaoMap: KakaoMap) {
        // Initial map setup
//        moveCamera(37.5416, 127.0793)
        getMyMenus()
//        showSearchResultOnMap()
    }

    // 지도에서의 화면 이동
    fun moveCamera(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            mapController.kakaoMap.value?.let { map ->
                val cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(latitude, longitude))
                map.moveCamera(cameraUpdate)
                updateCurrentCenter()
            }
        }
    }
    
    // 지도 중앙 좌표 업데이트
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

    // 지도에 핀 추가
    fun addMarker(latitude: Double, longitude: Double, resourceId: Int) {
        viewModelScope.launch {
            mapController.kakaoMap.value?.let { map ->
                val style = map.labelManager?.addLabelStyles(
                    LabelStyles.from(LabelStyle.from(resourceId))
                )
                val options = LabelOptions.from(LatLng.from(latitude, longitude)).setStyles(style).setClickable(true)
                map.labelManager?.layer?.addLabel(options)
                map.setOnLabelClickListener { kakaoMap, labelLayer, label ->
                    // 핀 클릭시 동작 정의
                    Log.d("SearchMenuViewModel", "핀 클릭됨")
                    moveCamera(latitude = label.position.latitude, longitude = label.position.longitude)
                    
                    // Find the matching menu item and call getMapDetail
                    _myMenus.value?.find { menu ->
                        menu.mapY == label.position.latitude && menu.mapX == label.position.longitude
                    }?.let { matchingMenu ->
                        Log.d("SearchMenuViewModel", "핀 클릭된 메뉴: ${matchingMenu.mapId}")
                        getMapDetail(matchingMenu.mapId)
                    }
                    
                    true
                }
            }
        }
    }

    // 지도의 전체 핀 제거
    fun clearMarkers() {
        viewModelScope.launch {
            mapController.kakaoMap.value?.let { map ->
                map.labelManager?.layer?.removeAll()
            }
        }
    }

    fun getSearchHistory() {
        viewModelScope.launch {
            val response = mapRepository.getMapSearchHistory()
            response.onSuccess {
                _searchHistory.value = it
                Log.d("SearchMenuViewModel", "크롤링 기록 조회 성공")
            }.onFailure {
                // Handle error
                Log.d("SearchMenuViewModel", "크롤링 기록 조회 실패 : ${it.message}")
            }
        }
    }

    fun getMapSearchResult(
        query: String,
        long: Double,
        lat: Double
    ) {
        viewModelScope.launch {
            Log.d("SearchMenuViewModel", "크롤링 스토어 정보 요청: $query, 좌표($lat, $long)")
            
            val response = mapRepository.getMapSearch(
                title = query,
                longitude = long,
                latitude = lat
            )
            
            response.onSuccess { result ->
                if (result != null) {
                    Log.d("SearchMenuViewModel", "크롤링 스토어 정보 조회 성공: ${result.size}개")
                    Log.d("SearchMenuViewModel", "크롤링 스토어 정보 조회 성공: ${result[0].storeTitle}")
                    // 검색 결과 저장
                    _searchResult.value = result
                }
            }.onFailure {
                Log.d("SearchMenuViewModel", "크롤링 스토어 정보 조회 실패: ${it.message}")
            }
        }
    }

    // 등록한 전체 메뉴 조회(빈 검색시 수행?)
    fun getMyMenus(){
        viewModelScope.launch {
            val response = mapRepository.getMap()
            response.onSuccess {
                if (it != null) {
                    _myMenus.value = it
                    Log.d("SearchMenuViewModel", "내 메뉴 조회 성공: ${it.size}개")
                    showSearchResultOnMap()
                } else {
                    Log.d("SearchMenuViewModel", "내 메뉴 조회 실패: null")
                }
            }.onFailure {
                Log.d("SearchMenuViewModel", "내 메뉴 조회 실패: ${it.message}")
            }
        }
    }

    // mapId에 위치하는 메뉴 조회(메뉴핀 누른 경우)
    fun getMapDetail(mapId: Long) {
        viewModelScope.launch {
            val response = mapRepository.getMapDetail(mapId)
            response.onSuccess {
                if (it != null) {
                    _menusOnPin.value = it
                    Log.d("SearchMenuViewModel", "핀 위치의 메뉴 조회 성공: $it")
                    showSearchResultOnMap()
                } else {
                    Log.d("SearchMenuViewModel", "핀 위치의 메뉴 조회 실패: null")
                }
            }.onFailure {
                Log.d("SearchMenuViewModel", "핀 위치의 메뉴 조회 실패: ${it.message}")
            }
        }
    }

    // 지도에 검색 결과 핀 추가
    fun showSearchResultOnMap() {
        viewModelScope.launch {
            clearMarkers()
            myMenus.value?.forEach { store ->
                val latitude = store.mapY
                val longitude = store.mapX
                addMarker(latitude, longitude, R.drawable.img_popup_dice)
                Log.d("SearchMenuViewModel", "mapId: ${store.mapId} lat: (${latitude}, long: ${longitude})")
            }
            // 첫 번째 검색 결과로 카메라 이동
            myMenus.value?.get(0)?.let { moveCamera(it.mapY, it.mapX) }
        }
    }
}