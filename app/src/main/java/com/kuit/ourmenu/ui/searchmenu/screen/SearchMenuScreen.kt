package com.kuit.ourmenu.ui.searchmenu.screen

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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.GoToMapButton
import com.kuit.ourmenu.ui.common.SearchTextField
import com.kuit.ourmenu.ui.common.bottomsheet.BottomSheetDragHandle
import com.kuit.ourmenu.ui.common.map.MapViewWithLifecycle
import com.kuit.ourmenu.ui.common.topappbar.OurMenuAddButtonTopAppBar
import com.kuit.ourmenu.ui.menuinfo.dummy.MenuInfoDummyData
import com.kuit.ourmenu.ui.searchmenu.component.SearchBottomSheetContent
import com.kuit.ourmenu.ui.searchmenu.component.SearchHistoryList
import com.kuit.ourmenu.ui.searchmenu.model.SearchHistoryData
import com.kuit.ourmenu.ui.searchmenu.viewmodel.SearchMenuViewModel
import com.kuit.ourmenu.ui.theme.NeutralWhite
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
    
    // 지도 중심 좌표 상태
    val currentCenter by viewModel.currentCenter.collectAsState()
    
    // Collect history data from ViewModel
    val searchHistory by viewModel.searchHistory.collectAsState()

    val density = LocalDensity.current

    val mapView = MapViewWithLifecycle(
        mapController = viewModel.mapController
    ) { kakaoMap ->
        // viewModel에서 kakaoMap을 초기화
        viewModel.initializeMap(kakaoMap)
    }

//    // 지도 초기화 후 카메라 위치 및 마커 추가 (테스트용)
//    LaunchedEffect(Unit) {
//        delay(2000)
//        viewModel.moveCamera(37.5665, 126.9780) // Seoul City Hall coordinates
//        viewModel.addMarker(37.5665, 126.9780, R.drawable.img_popup_dice)
//    }

    LaunchedEffect(searchBarFocused) {
        if (searchBarFocused) {
            showSearchBackground = true
            showBottomSheet = false
            
            // Fetch crawling history when search field is focused
            launch {
                viewModel.getCrawlingHistory()
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
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        val heightPx = coordinates.size.height
                        bottomSheetPeekHeight = density.run {
                            heightPx.toDp() + dragHandleHeight
                        }
                    },
                dataList = listOf(
                    MenuInfoDummyData.dummyData,
                    MenuInfoDummyData.dummyData,
                )
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
        sheetPeekHeight = if(showBottomSheet) bottomSheetPeekHeight else 0.dp , // TODO : 아이템 개수에 따라 높이 조절 필요
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
                // Convert CrawlingHistoryResponse to SearchHistoryData
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
                        
                        // 검색어와 현재 좌표로 크롤링 스토어 정보 요청
                        viewModel.getCrawlingStoreInfo(
                            query = searchText,
                            mapX = longitude,  // 경도
                            mapY = latitude    // 위도
                        )
                        
                        showBottomSheet = true
                    }
                }
            }

            GoToMapButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 16.dp, end = 20.dp),
                onClick = { 
                    // Example of map manipulation in response to button click
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