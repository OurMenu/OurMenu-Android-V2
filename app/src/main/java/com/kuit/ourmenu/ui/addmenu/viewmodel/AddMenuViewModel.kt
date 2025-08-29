package com.kuit.ourmenu.ui.addmenu.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.LocationServices
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import com.kuit.ourmenu.R
import com.kuit.ourmenu.data.model.map.response.CrawlingHistoryResponse
import com.kuit.ourmenu.data.model.map.response.CrawlingStoreDetailResponse
import com.kuit.ourmenu.data.model.map.response.CrawlingStoreInfoResponse
import com.kuit.ourmenu.data.repository.MapRepository
import com.kuit.ourmenu.ui.common.map.MapController
import com.kuit.ourmenu.utils.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMenuViewModel @Inject constructor(
    private val mapRepository: MapRepository,
    private val preferencesManager: PreferencesManager
) : ViewModel() {
    // 최근 검색 결과를 저장
    private val _searchHistory = MutableStateFlow<List<CrawlingHistoryResponse>?>(emptyList())
    val searchHistory = _searchHistory.asStateFlow()

    //실제 검색 결과를 저장
    private val _searchResult = MutableStateFlow<List<CrawlingStoreDetailResponse>?>(emptyList())
    val searchResult = _searchResult.asStateFlow()

    //식당 정보
    private val _storeInfo = MutableStateFlow<CrawlingStoreDetailResponse?>(null)
    val storeInfo = _storeInfo.asStateFlow()

    // 선택된 메뉴 인덱스
    private val _selectedMenuIndex = MutableStateFlow<Int?>(null)
    val selectedMenuIndex = _selectedMenuIndex.asStateFlow()

    // 화면에 보이는 지도 상의 중심에 해당하는 좌표
    private val _currentCenter = MutableStateFlow<LatLng?>(null)
    val currentCenter: StateFlow<LatLng?> = _currentCenter

    val mapController = MapController()

    // Permission state
    private val _locationPermissionGranted = MutableStateFlow(false)
    val locationPermissionGranted: StateFlow<Boolean> = _locationPermissionGranted.asStateFlow()

    private val _selectedMenuData = MutableStateFlow<SelectedMenuData>(
        SelectedMenuData(
            menuName = "",
            price = "",
            storeName = "",
            address = ""
        )
    )
    val selectedMenuData: StateFlow<SelectedMenuData> = _selectedMenuData.asStateFlow()

    init {
        viewModelScope.launch {
            preferencesManager.locationPermissionGranted.collect { granted ->
                _locationPermissionGranted.value = granted
                Log.d("AddMenuViewModel", "location permission : $granted")
            }
        }
    }

    fun updateLocationPermission(granted: Boolean) {
        viewModelScope.launch {
            preferencesManager.setLocationPermissionGranted(granted)
        }
    }

    // 지도 초기화
    @SuppressLint("MissingPermission")
    fun initializeMap(kakaoMap: KakaoMap, context: Context) {
        // Initial map setup
        // Get current location and move camera
        Log.d("AddMenuViewModel", "initialize Map")
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                Log.d("AddMenuViewModel", "location success: lat=${it.latitude}, long=${it.longitude}")
                moveCamera(it.latitude, it.longitude)
//                addMarker(it.latitude, it.longitude, R.drawable.img_popup_dice)
            } ?: run {
                Log.d("AddMenuViewModel", "location fail")
                moveCamera(37.5416, 127.0793)
//                addMarker(37.5416, 127.0793, R.drawable.img_popup_dice)
            }
        }
    }

    fun updateSelectedMenu(index: Int) {
        Log.d("AddMenuViewModel", "index: $index")
        viewModelScope.launch {
            _selectedMenuIndex.value = if (_selectedMenuIndex.value == index) null else index
        }
    }


    // 지도에서의 화면 이동
    fun moveCamera(latitude: Double, longitude: Double) {
        mapController.kakaoMap.value?.let { map ->
            val cameraUpdate =
                CameraUpdateFactory.newCenterPosition(LatLng.from(latitude, longitude))
            map.moveCamera(cameraUpdate)
            updateCurrentCenter()
        }
    }

    // 지도 중앙 좌표 업데이트
    fun updateCurrentCenter() {
        viewModelScope.launch {
            mapController.kakaoMap.value?.let { map ->
                val center = map.cameraPosition?.position
                _currentCenter.value = center
                if (center != null) {
                    Log.d(
                        "SearchMenuViewModel",
                        "현재 지도 중심 좌표: ${center.latitude}, ${center.longitude}"
                    )
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
        mapController.kakaoMap.value?.let { map ->
            val style = map.labelManager?.addLabelStyles(
                LabelStyles.from(LabelStyle.from(resourceId))
            )
            val options = LabelOptions.from(LatLng.from(latitude, longitude)).setStyles(style)
            map.labelManager?.layer?.addLabel(options)
            map.setOnLabelClickListener { kakaoMap, labelLayer, label ->
                // Find the store that matches the clicked label's coordinates
                val clickedStore = searchResult.value?.find { store ->
                    store.storeMapY == label.position.latitude && store.storeMapX == label.position.longitude
                }
                // Update storeInfo with the clicked store
                clickedStore?.let { store ->
                    _storeInfo.value = store
                }
                // Move camera to the clicked location
                moveCamera(latitude = label.position.latitude, longitude = label.position.longitude)
                true
            }
        }
    }

    // 지도의 전체 핀 제거
    fun clearMarkers() {
        mapController.kakaoMap.value?.let { map ->
            map.labelManager?.layer?.removeAll()
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

    fun getCrawlingStoreInfo(
        query: String,
        long: Double,
        lat: Double
    ) {
        viewModelScope.launch {
            Log.d("SearchMenuViewModel", "크롤링 스토어 정보 요청: $query, 좌표($lat, $long)")

            val response = mapRepository.getCrawlingStoreInfo(
                query = query,
                longitude = long,
                latitude = lat
            )

            response.onSuccess { result ->
                if (result != null) {
                    Log.d("SearchMenuViewModel", "크롤링 스토어 정보 조회 성공: ${result.size}개")
                    Log.d("SearchMenuViewModel", "크롤링 스토어 정보 조회 성공: ${result[0].storeTitle}")
                    // 검색 결과 저장
                    getCrawlingStoreDetail(result)
                }
            }.onFailure {
                Log.d("SearchMenuViewModel", "크롤링 스토어 정보 조회 실패: ${it.message}")
            }
        }
    }

    // 크롤링 한 세부 정보를 _searchResult에 저장하는 함수
    fun getCrawlingStoreDetail(crawledDatas: List<CrawlingStoreInfoResponse>) {
        val updatedResult = mutableListOf<CrawlingStoreDetailResponse>()
        viewModelScope.launch {
            crawledDatas.forEach { crawledData ->
                val response = mapRepository.getCrawlingStoreDetail(
                    isCrawled = true,
                    storeId = crawledData.storeId
                )
                response.onSuccess {
                    if (it != null) {
                        updatedResult.add(it)
                        Log.d("SearchMenuViewModel", "크롤링 스토어 정보 업데이트 성공: $it")
                    } else {
                        Log.d("SearchMenuViewModel", "크롤링 스토어 정보 업데이트 실패: null")
                    }
                }.onFailure {
                    Log.d("SearchMenuViewModel", "크롤링 스토어 정보 업데이트 실패: ${it.message}")
                }
            }
            _searchResult.value = updatedResult
            // Set the first store as the initial storeInfo
            updatedResult.firstOrNull()?.let { firstStore ->
                _storeInfo.value = firstStore
            }
            showSearchResultOnMap()
        }
    }

    // 지도에 검색 결과 핀 추가
    fun showSearchResultOnMap() {
        clearMarkers()
        searchResult.value?.forEach { store ->
            val latitude = store.storeMapY
            val longitude = store.storeMapX
            addMarker(latitude, longitude, R.drawable.img_popup_dice)
            Log.d(
                "SearchMenuViewModel",
                "마커 추가: ${store.storeTitle} lat: (${latitude}, long: ${longitude})"
            )
        }
        // 첫 번째 검색 결과로 카메라 이동
        searchResult.value?.get(0)?.let { moveCamera(it.storeMapY, it.storeMapX) }
    }
}

data class SelectedMenuData(
    val menuName: String = "",
    val price: String = "",
    val storeName: String = "",
    val address: String = "",
)