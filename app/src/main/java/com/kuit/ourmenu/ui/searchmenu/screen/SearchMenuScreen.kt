package com.kuit.ourmenu.ui.searchmenu.screen

import android.Manifest
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.GoToMapButton
import com.kuit.ourmenu.ui.common.SearchTextField
import com.kuit.ourmenu.ui.common.bottomsheet.BottomSheetDragHandle
import com.kuit.ourmenu.ui.common.map.mapViewWithLifecycle
import com.kuit.ourmenu.ui.common.topappbar.OurMenuAddButtonTopAppBar
import com.kuit.ourmenu.ui.searchmenu.component.SearchBottomSheetContent
import com.kuit.ourmenu.ui.searchmenu.component.SearchHistoryList
import com.kuit.ourmenu.ui.searchmenu.model.SearchHistoryData
import com.kuit.ourmenu.ui.searchmenu.viewmodel.SearchMenuViewModel
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.utils.PermissionHandler
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchMenuScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchMenuViewModel = hiltViewModel(),
) {

    val scaffoldState = rememberBottomSheetScaffoldState()
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    var dragHandleHeight by remember { mutableStateOf(0.dp) }
    var bottomSheetPeekHeight by remember { mutableStateOf(0.dp) }
    var showSearchBackground by rememberSaveable { mutableStateOf(false) }
    var searchText by rememberSaveable { mutableStateOf("") }
    var searchActionDone by rememberSaveable { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val searchBarFocused by interactionSource.collectIsFocusedAsState()
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val locationPermissionGranted by viewModel.locationPermissionGranted.collectAsStateWithLifecycle()

    // 지도 중심 좌표
    val currentCenter by viewModel.currentCenter.collectAsState()
    
    // 검색기록
    val searchHistory by viewModel.searchHistory.collectAsState()
    
    // 핀 위치에 해당하는 메뉴들
    val menusOnPin by viewModel.menusOnPin.collectAsState()

    val density = LocalDensity.current
    val singleItemHeight = 300.dp // Fixed height for each item

    LaunchedEffect(menusOnPin) {
        if (menusOnPin != null && menusOnPin?.isNotEmpty() == true) {
            showBottomSheet = true
        }
    }

    // 권한 허용이 안된 경우 권한 요청
    if (!locationPermissionGranted) {
        PermissionHandler(
            permission = Manifest.permission.ACCESS_FINE_LOCATION,
            rationaleMessage = "Location permission is required to show your current location on the map",
            onPermissionGranted = {
                viewModel.updateLocationPermission(true)
            },
            onPermissionDenied = {
                viewModel.updateLocationPermission(false)
            }
        )
    }

    val mapView = mapViewWithLifecycle(
        mapController = viewModel.mapController
    ) { kakaoMap ->
        // viewModel에서 kakaoMap을 초기화
        viewModel.initializeMap(kakaoMap, context)
    }

    // permission 여부 변한 경우에 발생
    LaunchedEffect(locationPermissionGranted) {
        if (locationPermissionGranted) {
            viewModel.mapController.kakaoMap.value?.let { kakaoMap ->
                viewModel.initializeMap(kakaoMap, context)
            }
        }
    }

    LaunchedEffect(searchBarFocused) {
        if (searchBarFocused) {
            showSearchBackground = true
            showBottomSheet = false
            
            // Fetch crawling history when search field is focused
            launch {
                viewModel.getSearchHistory()
            }
        }
    }

    BackHandler(enabled = showSearchBackground) {
        if (searchBarFocused) focusManager.clearFocus()
        searchActionDone = false
        showSearchBackground = false
        searchText = ""
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxWidth(),
        topBar = { OurMenuAddButtonTopAppBar() },
        sheetContent = {
            SearchBottomSheetContent(
                modifier = Modifier.fillMaxWidth(),
                dataList = menusOnPin ?: emptyList()
            )
        },
        sheetContainerColor = NeutralWhite,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetDragHandle = {
            BottomSheetDragHandle(
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    dragHandleHeight = density.run { coordinates.size.height.toDp() }
                }
            )
        },
        sheetPeekHeight = if(showBottomSheet) {
            val itemCount = menusOnPin?.size ?: 0
            (singleItemHeight * itemCount) + dragHandleHeight
        } else 0.dp,
        containerColor = NeutralWhite,
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (!showSearchBackground) {
                //지도 컴포넌트
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AndroidView(
                        modifier = Modifier,
                        factory = { mapView }
                    )
                }
            } else {
                val historyDataList = searchHistory?.map { history ->
                    SearchHistoryData(
                        menuTitle = history.menuTitle,
                        storeTitle = history.storeAddress.split(',').firstOrNull() ?: "",
                        address = history.storeAddress
                    )
                } ?: emptyList()
                
                SearchHistoryList(
                    historyList = historyDataList,
                    onClick = {
                        // 크롤링 기록 아이템 클릭시 동작
                        showSearchBackground = false
                        showBottomSheet = true
                    }
                )
            }

            SearchTextField(
                modifier = Modifier.padding(top = 12.dp, start = 20.dp, end = 20.dp),
                text = searchText,
                onTextChange = {
                    searchText = it
                    showSearchBackground = true
                    showBottomSheet = false
                },
                placeHolder = R.string.search_place_holder,
                interactionSource = interactionSource
            ) {
                // onSearch 함수
                if (searchBarFocused) focusManager.clearFocus()
                searchActionDone = true
                
                // 검색 시 현재 지도 중심 좌표 사용
                if (searchText.isNotEmpty()) {
                    // 검색 직전에 현재 지도 중심 좌표 업데이트
                    viewModel.updateCurrentCenter()
                    
                    val center = viewModel.getCurrentCoordinates()
                    if (center != null) {
                        val (latitude, longitude) = center
                        Log.d("SearchMenuScreen", "검색 위치: $latitude, $longitude")
                        
                        // 검색어와 현재 좌표로 스토어 정보 요청
                        viewModel.getMapSearchResult(
                            query = searchText,
                            long = longitude,
                            lat = latitude
                        )
                        
                        showBottomSheet = true
                        showSearchBackground = false
                    }
                }
            }

            GoToMapButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 16.dp, end = 20.dp),
                onClick = { 
                    // TODO: 임시로 설정해놓은 카메라 이동
                    viewModel.moveCamera(37.5416, 127.0793)
                },
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun SearchMenuScreenPreview() {
    SearchMenuScreen()
}