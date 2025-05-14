package com.kuit.ourmenu.ui.addmenu.viewmodel

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
import com.kuit.ourmenu.data.model.map.response.CrawlingStoreDetailResponse
import com.kuit.ourmenu.data.model.map.response.CrawlingStoreInfoResponse
import com.kuit.ourmenu.data.repository.MapRepository
import com.kuit.ourmenu.ui.common.map.MapController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMenuViewModel @Inject constructor(
    private val mapRepository: MapRepository
) : ViewModel() {
    // 최근 검색 결과를 저장
    private val _searchHistory = MutableStateFlow<List<CrawlingHistoryResponse>?>(emptyList())
    val searchHistory = _searchHistory.asStateFlow()

    //실제 검색 결과를 저장
    private val _searchResult = MutableStateFlow<List<CrawlingStoreDetailResponse>?>(emptyList())
    val searchResult = _searchResult.asStateFlow()

    //식당 정보
    private val _storeInfo = MutableStateFlow(AddMenuDummyStoreInfo())
    val storeInfo: StateFlow<AddMenuDummyStoreInfo> = _storeInfo

    // 화면에 보이는 지도 상의 중심에 해당하는 좌표
    private val _currentCenter = MutableStateFlow<LatLng?>(null)
    val currentCenter: StateFlow<LatLng?> = _currentCenter

    val mapController = MapController()

    init {
//        getRecentSearchResults()
//        //확인용, 이후에는 제거
//        getSearchResults()
//        getRestaurantInfo()

    }

    // 지도 초기화
    fun initializeMap(kakaoMap: KakaoMap) {
        // Initial map setup
        moveCamera(37.5416, 127.0793)
        addMarker(37.5406, 127.0763, R.drawable.img_popup_dice)
    }

    fun getRestaurantInfo() {
        viewModelScope.launch {
            _storeInfo.value = AddMenuDummyStoreInfo(
                imgList = listOf(
                    R.drawable.img_dummy_pizza,
                    R.drawable.img_dummy_pizza,
                    R.drawable.img_dummy_pizza,
                ),
                name = R.string.our_ddeokbokki.toString(),
                address = R.string.resaturant_address.toString(),
                menuList = listOf(false, false, false, false, false, false, false, false)
            )
        }
    }

    fun updateSelectedMenu(index: Int) {
        Log.d("AddMenuViewModel", "index: $index")
        viewModelScope.launch {
            val currentState = _storeInfo.value.menuList[index]
            val updatedMenuList =
                if (currentState){
                    //선택된 아이템 클릭시 false로 다시 변경
                    _storeInfo.value.menuList.map {
                        false
                    }
                }else{
                    //클릭된 인덱스만 true, 나머지는 false
                    _storeInfo.value.menuList.mapIndexed { i, _ ->
                        i == index
                }
            }
//            Log.d("AddMenuViewModel", "updatedMenuList: $updatedMenuList")
            _storeInfo.value = _storeInfo.value.copy(menuList = updatedMenuList)
        }
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
                val options = LabelOptions.from(LatLng.from(latitude, longitude)).setStyles(style)
                map.labelManager?.layer?.addLabel(options)
                map.setOnLabelClickListener { kakaoMap, labelLayer, label ->
                    // TODO: 핀 클릭시 동작 정의
                    Log.d("SearchMenuViewModel", "핀 클릭됨")
                    moveCamera(latitude = label.position.latitude, longitude = label.position.longitude)
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

                // 지도에 마커 추가 (필요한 경우)
                // val store = result[0]
                // val lat = store.latitude
                // val long = store.longitude

//                // 마커 초기화
//                clearMarkers()
//
//                // 마커 추가
//                addMarker(lat, long, R.drawable.img_popup_dice)

                // 카메라 이동 (필요한 경우)
                // moveCamera(lat, long)
            }.onFailure {
                Log.d("SearchMenuViewModel", "크롤링 스토어 정보 조회 실패: ${it.message}")
            }
        }
    }

    // 크롤링 한 세부 정보를 _searchResult에 저장하는 함수
    fun getCrawlingStoreDetail(crawledDatas: List<CrawlingStoreInfoResponse>){
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
                        Log.d("SearchMenuViewModel", "크롤링 스토어 정보 업데이트 성공: ${it.storeTitle}")
                    }else{
                        Log.d("SearchMenuViewModel", "크롤링 스토어 정보 업데이트 실패: null")
                    }
                }.onFailure {
                    Log.d("SearchMenuViewModel", "크롤링 스토어 정보 업데이트 실패: ${it.message}")
                }
            }
            _searchResult.value = updatedResult
            showSearchResultOnMap()
        }
    }

    // 지도에 검색 결과 핀 추가
    fun showSearchResultOnMap() {
        viewModelScope.launch {
            clearMarkers()
            searchResult.value?.forEach { store ->
                val latitude = store.storeMapY
                val longitude = store.storeMapX
                addMarker(latitude, longitude, R.drawable.img_popup_dice)
                Log.d("SearchMenuViewModel", "마커 추가: ${store.storeTitle} lat: (${latitude}, long: ${longitude})")
            }
            // 첫 번째 검색 결과로 카메라 이동
            searchResult.value?.get(0)?.let { moveCamera(it.storeMapY, it.storeMapX) }
        }
    }
}

// TODO: 이후에 dto 반영시 삭제 예정
data class AddMenuDummyStoreInfo(
    val imgList: List<Int> = emptyList(),
    val name: String = "",
    val address: String = "",
    val menuList: List<Boolean> = emptyList(),
)