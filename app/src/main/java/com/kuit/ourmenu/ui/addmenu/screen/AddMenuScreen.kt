package com.kuit.ourmenu.ui.addmenu.screen

import android.Manifest
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kuit.ourmenu.R
import com.kuit.ourmenu.data.model.map.response.CrawlingStoreDetailResponse
import com.kuit.ourmenu.ui.addmenu.component.AddMenuSearchBackground
import com.kuit.ourmenu.ui.addmenu.component.bottomsheet.AddMenuBottomSheetContent
import com.kuit.ourmenu.ui.addmenu.viewmodel.AddMenuViewModel
import com.kuit.ourmenu.ui.common.SearchTextField
import com.kuit.ourmenu.ui.common.map.mapViewWithLifecycle
import com.kuit.ourmenu.ui.common.topappbar.OurMenuBackButtonTopAppBar
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography
import com.kuit.ourmenu.utils.PermissionHandler
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMenuScreen(
    modifier: Modifier = Modifier,
    viewModel: AddMenuViewModel = hiltViewModel(),
    onNavigateToAddMenuInfo: () -> Unit
) {
    var scaffoldState = rememberBottomSheetScaffoldState()
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    var showSearchBackground by rememberSaveable { mutableStateOf(false) }
    var searchText by rememberSaveable { mutableStateOf("") }
    var searchActionDone by rememberSaveable { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val searchBarFocused by interactionSource.collectIsFocusedAsState()
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val recentSearchResults by viewModel.searchHistory.collectAsStateWithLifecycle()
    val searchResults by viewModel.searchResult.collectAsStateWithLifecycle()
    val storeInfo by viewModel.storeInfo.collectAsStateWithLifecycle()
    val selectedMenuIndex by viewModel.selectedMenuIndex.collectAsStateWithLifecycle() // storeInfo에서 선택된 메뉴의 인덱스
    val locationPermissionGranted by viewModel.locationPermissionGranted.collectAsStateWithLifecycle()

    // 지도 중심 좌표 상태
    val currentCenter by viewModel.currentCenter.collectAsState()

    // Collect history data from ViewModel
    val searchHistory by viewModel.searchHistory.collectAsState()

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
        // 이미 permission 허용한 경우
        if (locationPermissionGranted) {
            viewModel.initializeMap(kakaoMap, context)
        }
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

            scope.launch {
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
        topBar = {
            OurMenuBackButtonTopAppBar {
                Text(
                    text = stringResource(R.string.ourmenu),
                    style = ourMenuTypography().pretendard_600_18,
                    color = Primary500Main,
                )
            }
        },
        sheetContainerColor = Color.White,
        sheetContent = {
            //bottom sheet 구성
            AddMenuBottomSheetContent(
                scaffoldState = scaffoldState,
                storeInfo = storeInfo ?: CrawlingStoreDetailResponse(
                    storeId = "",
                    storeTitle = "",
                    storeAddress = "",
                    storeImgs = emptyList(),
                    menus = emptyList(),
                    storeMapX = 0.0,
                    storeMapY = 0.0
                ),
                selectedMenuIndex = selectedMenuIndex,
                onNavigateToAddMenuInfo = {
                    onNavigateToAddMenuInfo()
                },
                onItemClick = { index -> viewModel.updateSelectedMenu(index) })
        },
        //조건 만족하면 bottom sheet 보여주고, 아니면 화면에 안보이도록 처리
        sheetPeekHeight = if (showBottomSheet) 254.dp else 0.dp,
        sheetDragHandle = {
            // 커스텀 핸들
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .height(4.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Neutral300)
                )
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
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
                    ) { view ->

                    }
                }
            } else {
                //검색 컴포넌트
                AddMenuSearchBackground(
                    searchActionDone = searchActionDone,
                    searchHistory = recentSearchResults,
                    searchResults = searchResults
                ) {
                    //검색된 아이템 클릭시 작동할 함수
                    if (searchBarFocused) focusManager.clearFocus()
                    showSearchBackground = false
                    showBottomSheet = true
                    searchText = ""
                }
            }

            SearchTextField(
                modifier = Modifier.padding(top = 12.dp, start = 20.dp, end = 20.dp),
                text = searchText,
                onTextChange = {
                    searchText = it
                    showSearchBackground = true
                    showBottomSheet = false
                },
                interactionSource = interactionSource,
                placeHolder = R.string.search_store_name
            ) {
                //onSearch 함수
                if (searchBarFocused) focusManager.clearFocus()
                searchActionDone = true

                if (searchText.isNotEmpty()){
                    viewModel.updateCurrentCenter()

                    val center = viewModel.getCurrentCoordinates()
                    if (center != null) {
                        val (latitude, longitude) = center
                        Log.d("SearchMenuScreen", "검색 위치: $latitude, $longitude")

                        // 검색어와 현재 좌표로 크롤링 스토어 정보 요청
                        viewModel.getCrawlingStoreInfo(
                            query = searchText,
                            long = longitude,
                            lat = latitude
                        )

                        showBottomSheet = true
                        showSearchBackground = false
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddMenuScreenPreview() {
    val viewModel : AddMenuViewModel = hiltViewModel()
    AddMenuScreen(
        modifier = Modifier,
        viewModel = viewModel,
        onNavigateToAddMenuInfo = {}
    )
}