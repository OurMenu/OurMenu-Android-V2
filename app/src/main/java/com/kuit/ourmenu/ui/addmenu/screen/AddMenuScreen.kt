package com.kuit.ourmenu.ui.addmenu.screen

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.addmenu.component.AddMenuSearchBackground
import com.kuit.ourmenu.ui.addmenu.component.bottomsheet.AddMenuBottomSheetContent
import com.kuit.ourmenu.ui.addmenu.viewmodel.AddMenuSearchViewModel
import com.kuit.ourmenu.ui.common.SearchTextField
import com.kuit.ourmenu.ui.common.map.MapViewWithLifecycle
import com.kuit.ourmenu.ui.common.topappbar.OurMenuBackButtonTopAppBar
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMenuScreen(navController: NavController) {
    var scaffoldState = rememberBottomSheetScaffoldState()
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    var showSearchBackground by rememberSaveable { mutableStateOf(false) }
    var searchText by rememberSaveable { mutableStateOf("") }
    var searchActionDone by rememberSaveable { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val searchBarFocused by interactionSource.collectIsFocusedAsState()
    val focusManager = LocalFocusManager.current

    val viewModel: AddMenuSearchViewModel = viewModel()
    val recentSearchResults by viewModel.recentSearchResults.collectAsStateWithLifecycle()
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()
    val storeInfo by viewModel.storeInfo.collectAsStateWithLifecycle()

    val mapView = MapViewWithLifecycle() { kakaoMap ->
        // 카메라 이동
        val cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(37.5416, 127.0793))
        // 라벨 아이콘 설정
        val style = kakaoMap.labelManager?.addLabelStyles(
            // R.drawable에서 vector를 가져오면 화면에 보이지 않는 이슈 존재
            LabelStyles.from(LabelStyle.from(R.drawable.img_popup_dice))
        )
        // 라벨 아이콘 적용
        val options = LabelOptions.from(LatLng.from(37.5406, 127.0763)).setStyles(style)
        // 레이어
        val layer = kakaoMap.labelManager?.layer
        layer?.addLabel(options)
        kakaoMap.moveCamera(cameraUpdate)
    }

    LaunchedEffect(searchBarFocused) {
        if (searchBarFocused) {
            showSearchBackground = true
            showBottomSheet = false
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
                storeInfo = storeInfo,
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
                    recentSearchResults = recentSearchResults,
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
                interactionSource = interactionSource
            ) {
                //onSearch 함수
                if (searchBarFocused) focusManager.clearFocus()
                searchActionDone = true

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddMenuScreenPreview() {
    val navController = rememberNavController()

    AddMenuScreen(navController)
}