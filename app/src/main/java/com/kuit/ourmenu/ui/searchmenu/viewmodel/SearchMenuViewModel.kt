package com.kuit.ourmenu.ui.searchmenu.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.core.graphics.scale
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
import com.kuit.ourmenu.data.model.map.response.MapDetailResponse
import com.kuit.ourmenu.data.model.map.response.MapResponse
import com.kuit.ourmenu.data.model.map.response.MapSearchHistoryResponse
import com.kuit.ourmenu.data.model.map.response.MapSearchResponse
import com.kuit.ourmenu.data.repository.MapRepository
import com.kuit.ourmenu.ui.common.map.MapController
import com.kuit.ourmenu.utils.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class SearchMenuViewModel @Inject constructor(
    private val mapRepository: MapRepository,
    private val preferencesManager: PreferencesManager
) : ViewModel() {
    // 검색 기록
    private val _searchHistory = MutableStateFlow<List<MapSearchHistoryResponse>?>(emptyList())
    val searchHistory = _searchHistory.asStateFlow()

    // 전체 등록된 메뉴
    private val _myMenus = MutableStateFlow<List<MapResponse>?>(emptyList())
    val myMenus = _myMenus.asStateFlow() // TODO: 리팩토링

    // 검색 결과
    private val _searchResult = MutableStateFlow<List<MapSearchResponse>?>(emptyList())
    val searchResult = _searchResult.asStateFlow()

    // 위치에 따른 메뉴 조회
    private val _menusOnPin = MutableStateFlow<List<MapDetailResponse>?>(emptyList())
    val menusOnPin = _menusOnPin.asStateFlow()

    // 화면에 보이는 지도 상의 중심에 해당하는 좌표
    private val _currentCenter = MutableStateFlow<LatLng?>(null)
    val currentCenter: StateFlow<LatLng?> = _currentCenter

    // 현재 활성화된 라벨의 mapId를 추적
    private val _activeMapId = MutableStateFlow<Long?>(null)
    val activeMapId = _activeMapId.asStateFlow()

    val mapController = MapController()

    // Permission state
    private val _locationPermissionGranted = MutableStateFlow(false)
    val locationPermissionGranted: StateFlow<Boolean> = _locationPermissionGranted.asStateFlow()

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
//        moveCamera(37.5416, 127.0793)
        getMyMenus()
//        showSearchResultOnMap()
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

    // URL에서 이미지를 비트맵으로 로드하는 함수
    private suspend fun loadImageFromUrl(url: String): Bitmap? = withContext(Dispatchers.IO) {
        return@withContext try {
            val connection = URL(url).openConnection()
            connection.connectTimeout = 5000
            connection.readTimeout = 5000
            connection.connect()

            val input = connection.getInputStream()
            // 원본 비트맵 로드
            val originalBitmap = BitmapFactory.decodeStream(input)
            
            // 원하는 크기로 리사이징 (예: 원본의 2배)
            originalBitmap?.let { bitmap ->
                val width = bitmap.width * 2  // 원본 너비의 2배
                val height = bitmap.height * 2  // 원본 높이의 2배
                bitmap.scale(width, height).also {
                    // 원본 비트맵 메모리 해제
                    if (it != bitmap) {
                        bitmap.recycle()
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("SearchMenuViewModel", "이미지 로드 실패: ${e.message}")
            null
        }
    }

    // 지도에 핀 추가
    private fun addMarker(store: MapResponse, isActive: Boolean = false) {
        viewModelScope.launch {
            val imageUrl = if (isActive) store.menuPinImgUrl else store.menuPinDisableImgUrl
            val bitmap = loadImageFromUrl(imageUrl)
            
            mapController.kakaoMap.value?.let { map ->
                val style = if (bitmap != null) {
                    map.labelManager?.addLabelStyles(
                        LabelStyles.from(
                            LabelStyle.from(bitmap).apply {
                                isApplyDpScale = true
                                setPadding(0f)
                                setAnchorPoint(0.5f, 0.5f)
                            }
                        )
                    )
                } else {
                    // 이미지 로드 실패시 기본 이미지 사용
                    map.labelManager?.addLabelStyles(
                        LabelStyles.from(
                            LabelStyle.from(R.drawable.img_popup_dice).apply {
                                isApplyDpScale = true
                                setPadding(0f)
                                setAnchorPoint(0.5f, 0.5f)
                            }
                        )
                    )
                }
                
                val options = LabelOptions.from(LatLng.from(store.mapY, store.mapX)).setStyles(style)
                    .setClickable(true)
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
                        // 현재 활성화된 mapId 업데이트
                        _activeMapId.value = matchingMenu.mapId
                        // 모든 마커 다시 그리기
                        refreshMarkers()
                        getMapDetail(matchingMenu.mapId)
                    }

                    true
                }
            }
        }
    }

    // 모든 마커 다시 그리기
    private fun refreshMarkers() {
        clearMarkers()
        myMenus.value?.forEach { store ->
            addMarker(store, store.mapId == _activeMapId.value)
        }
    }

    // 지도의 전체 핀 제거
    fun clearMarkers() {
        mapController.kakaoMap.value?.let { map ->
            map.labelManager?.layer?.removeAll()
        }
    }

    fun getSearchHistory() {
        viewModelScope.launch {
            val response = mapRepository.getMapSearchHistory()
            response.onSuccess {
                _searchHistory.value = it
                Log.d("SearchMenuViewModel", "검색 기록 조회 성공")
            }.onFailure {
                // Handle error
                Log.d("SearchMenuViewModel", "검색 기록 조회 실패 : ${it.message}")
            }
        }
    }

    fun getMapSearchResult(
        query: String,
        long: Double,
        lat: Double
    ) {
        viewModelScope.launch {
            Log.d("SearchMenuViewModel", "등록 메뉴 정보 요청: $query, 좌표($lat, $long)")

            val response = mapRepository.getMapSearch(
                title = query,
                longitude = long,
                latitude = lat
            )

            response.onSuccess { result ->
                if (result != null && result.isNotEmpty()) {
                    Log.d("SearchMenuViewModel", "등록 메뉴 정보 조회 성공: $result")
                    // 검색 결과 저장
                    _searchResult.value = result
                    
                    // 전체 메뉴 목록을 다시 가져온 후 필터링
                    val allMenusResponse = mapRepository.getMap()
                    allMenusResponse.onSuccess { allMenus ->
                        if (allMenus != null) {
                            // 전체 메뉴 중에서 검색 결과와 일치하는 것들만 필터링
                            _myMenus.value = allMenus.filter { menu ->
                                result.any { searchResult -> searchResult.mapId == menu.mapId }
                            }
                            // 검색 결과의 첫 번째 항목을 활성화 상태로 설정
                            _activeMapId.value = result.firstOrNull()?.mapId
                            showSearchResultOnMap()
                            // 첫 번째 검색 결과의 상세 정보를 가져와서 바텀시트에 표시
                            _activeMapId.value?.let { mapId ->
                                getMapDetail(mapId)
                            }
                            // 검색 결과를 검색 기록에 반영
                            if (result.firstOrNull()?.menuId != null){
                                mapRepository.getMapMenuDetail(result.first().menuId)
                                Log.d("SearchMenuViewModel", "검색 기록에 반영: ${result.first().menuId}")
                            }

                        }
                    }
                }
            }.onFailure {
                Log.d("SearchMenuViewModel", "등록 메뉴 정보 조회 실패: ${it.message}")
            }
        }
    }

    // 등록한 전체 메뉴 조회(빈 검색시 수행?)
    fun getMyMenus() {
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
                } else {
                    Log.d("SearchMenuViewModel", "핀 위치의 메뉴 조회 실패: null")
                }
            }.onFailure {
                Log.d("SearchMenuViewModel", "핀 위치의 메뉴 조회 실패: ${it.message}")
            }
        }
    }

    fun getMapMenuDetail(menuId: Long) {
        viewModelScope.launch {
            // 먼저 전체 메뉴를 가져옴
            val myMenusResponse = mapRepository.getMap()
            myMenusResponse.onSuccess { menus ->
                if (menus != null){
                    val allMenus = menus
                    Log.d("SearchMenuViewModel", "menuId로 메뉴 정보 요청: $menuId")
                    val menuDetailResponse = mapRepository.getMapMenuDetail(menuId)
                    menuDetailResponse.onSuccess { menuDetail ->
                        Log.d("SearchMenuViewModel", "메뉴 상세 조회 성공: $menuDetail")
                        // 검색 기록에서 해당 menuId를 가진 항목 찾기
                        searchHistory.value?.find { it.menuId == menuId }?.let { historyItem ->
                            Log.d("SearchMenuViewModel", "검색 기록에서 찾은 mapId: ${historyItem.mapId}")
                            // 가져온 전체 메뉴에서 필터링
                            _myMenus.value = allMenus.filter { menu ->
                                menu.mapId == historyItem.mapId
                            }
                            // 해당 mapId를 활성화 상태로 설정
                            _activeMapId.value = historyItem.mapId
                            // 지도에 검색 결과 표시
                            showSearchResultOnMap()
                            // 메뉴 상세 정보를 바텀시트에 표시하기 위해 설정
                            getMapDetail(historyItem.mapId)
                        }
                    }.onFailure {
                        Log.d("SearchMenuViewModel", "메뉴 상세 조회 실패: ${it.message}")
                    }
                }
            }.onFailure {
                Log.d("SearchMenuViewModel", "내 메뉴 조회 실패: ${it.message}")
            }
        }
    }

    // 지도에 검색 결과 핀 추가
    fun showSearchResultOnMap() {
        clearMarkers()
        _myMenus.value?.let { menus ->
            if (menus.isNotEmpty()) {
                menus.forEach { store ->
                    addMarker(store, store.mapId == _activeMapId.value)
                    Log.d(
                        "SearchMenuViewModel",
                        "mapId: ${store.mapId} lat: (${store.mapY}, long: ${store.mapX})"
                    )
                }
                // 첫 번째 검색 결과로 카메라 이동 TODO: 현재 위치랑 가까운 결과로 이동
                moveCamera(menus[0].mapY, menus[0].mapX)
            } else {
                Log.d("SearchMenuViewModel", "검색 결과가 없습니다.")
            }
        }
    }

    // 활성화된 맵 ID를 초기화하고 마커를 다시 그림
    fun clearActiveMapId() {
        _activeMapId.value = null
        refreshMarkers()
    }

    // 네이버맵 이동을 위한 가게명 조회
    suspend fun getWebSearchQuery(mapId: Long): String {
        val baseUrl = "https://map.naver.com/p/search/"
        val response = mapRepository.getMapDetail(mapId)
        return response.fold(
            onSuccess = { menuList ->
                if (menuList.isNullOrEmpty()) {
                    Log.d("SearchMenuViewModel", "메뉴 상세 조회 실패: 메뉴가 없습니다.")
                    ""
                } else {
                    baseUrl + menuList.first().storeTitle
                }
            },
            onFailure = { 
                Log.d("SearchMenuViewModel", "메뉴 상세 조회 실패: ${it.message}")
                ""
            }
        )
    }
}