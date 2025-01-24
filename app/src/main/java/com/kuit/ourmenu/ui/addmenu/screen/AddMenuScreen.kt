package com.kuit.ourmenu.ui.addmenu.screen

import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.addmenu.component.AddMenuTopAppBar
import com.kuit.ourmenu.ui.addmenu.component.bottomsheet.AddMenuBottomSheetContent
import com.kuit.ourmenu.ui.common.SearchBar
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMenuScreen(modifier: Modifier = Modifier) {
    var scaffoldState = rememberBottomSheetScaffoldState()
    var showBottomSheet by rememberSaveable { mutableStateOf(true) }
    var searchText by rememberSaveable { mutableStateOf("") }
    var searchActionDone by rememberSaveable { mutableStateOf(false) }
    var dummyRecentSearchResults = mutableListOf(
        false,
        false,
        false,
        false,
        true,
    )
    val dummySearchResults : MutableList<Boolean> = mutableListOf(
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AddMenuTopAppBar {
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
            AddMenuBottomSheetContent(scaffoldState)
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
            if (showBottomSheet) {
                //지도 컴포넌트
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("지도 컴포넌트")
                }
            } else {
                //검색 컴포넌트
                AddMenuSearchBackground(
                    searchActionDone = searchActionDone,
                    recentSearchResults = dummyRecentSearchResults,
                    searchResults = dummySearchResults
                )
            }

            SearchBar(
                modifier = Modifier.padding(top = 12.dp, start = 20.dp, end = 20.dp),
                text = searchText,
                onTextChange = { searchText = it },
            ) {
                //onSearch 함수
//                searchActionDone = true
                showBottomSheet = true
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddMenuScreenPreview() {
    AddMenuScreen()
}