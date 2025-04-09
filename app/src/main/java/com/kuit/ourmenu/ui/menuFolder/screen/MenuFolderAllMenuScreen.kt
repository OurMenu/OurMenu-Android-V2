package com.kuit.ourmenu.ui.menuFolder.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kuit.ourmenu.R
import com.kuit.ourmenu.data.model.menuFolder.response.SortOrderType
import com.kuit.ourmenu.ui.common.bottomsheet.BottomSheetDragHandle
import com.kuit.ourmenu.ui.common.topappbar.BackButtonTopAppBar
import com.kuit.ourmenu.ui.menuFolder.component.AddButton
import com.kuit.ourmenu.ui.menuFolder.component.FilterBottomSheet
import com.kuit.ourmenu.ui.menuFolder.component.MenuFolderMenuButton
import com.kuit.ourmenu.ui.menuFolder.component.SortDropdown
import com.kuit.ourmenu.ui.menuFolder.viewmodel.MenuFolderAllViewModel
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuFolderAllMenuScreen(
    onNavigateBack: () -> Unit,
    viewModel: MenuFolderAllViewModel = hiltViewModel()
) {
    val menus by viewModel.menuFolderAll.collectAsStateWithLifecycle()
    val selectedSort by viewModel.sortOrder.collectAsStateWithLifecycle()
    val menuCount = menus.size

    var filterCount by rememberSaveable { mutableIntStateOf(0) } // 선택된 필터 개수 상태 관리
    var selectedFilters by rememberSaveable { mutableStateOf(listOf<String>()) } // 선택된 필터 리스트

    val scaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(scaffoldState.bottomSheetState) {
        snapshotFlow { scaffoldState.bottomSheetState.currentValue }
            .collect { state ->
                Log.d("AddMenuTagScreen", "BottomSheetState changed: $state")
            }
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            BackButtonTopAppBar(Neutral500, false) {
                onNavigateBack()
            }
        },
        sheetContainerColor = NeutralWhite,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            FilterBottomSheet(
                categoryTagList = listOf(
                    R.drawable.ic_tag_rice to "밥",
                    R.drawable.ic_tag_rice to "빵",
                    R.drawable.ic_tag_rice to "면"
                ),
                nationalityTagList = listOf(
                    R.drawable.ic_tag_rice to "한식",
                    R.drawable.ic_tag_rice to "중식",
                    R.drawable.ic_tag_rice to "일식"
                ),
                tasteTagList = listOf(
                    R.drawable.ic_tag_rice to "매콤함",
                    R.drawable.ic_tag_rice to "달달함",
                    R.drawable.ic_tag_rice to "시원함"
                ),
                occasionTagList = listOf(
                    R.drawable.ic_tag_rice to "혼밥",
                    R.drawable.ic_tag_rice to "친구 약속",
                    R.drawable.ic_tag_rice to "데이트"
                ),
                onApplyButtonClick = {
                    coroutineScope.launch {
                        filterCount = selectedFilters.size // 적용 버튼 클릭 시 선택된 필터 개수 반영
                        scaffoldState.bottomSheetState.partialExpand() // 적용 버튼 클릭 시 BottomSheet 닫기
                    }

                },
                onSelectedTagsChange = { newSelectedTags -> selectedFilters = newSelectedTags },
            )
        },
        sheetDragHandle = {
            BottomSheetDragHandle()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.all_menu),
                        style = ourMenuTypography().pretendard_600_20,
                        color = Neutral900
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = String.format(stringResource(R.string.count), menuCount),
                        style = ourMenuTypography().pretendard_500_14,
                        color = Neutral700
                    )
                }

                SortDropdown(
                    options = SortOrderType.entries.map { it.displayName },
                    selectedOption = selectedSort.displayName,
                ) { selectedDisplayName ->
                    SortOrderType.entries
                        .firstOrNull { it.displayName == selectedDisplayName }
                        ?.let { viewModel.updateSortOrder(it) }
                }
            }

            // 필터 버튼 (필터 개수 반영)
            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(top = 24.dp, bottom = 16.dp, start = 20.dp)
                    .clickable {
                        coroutineScope.launch {
                            scaffoldState.bottomSheetState.expand() // 버튼 클릭 시 BottomSheet 열기
                        }
                    },
                colors = CardDefaults.cardColors(containerColor = Primary500Main),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_filter),
                        contentDescription = "filter button",
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "$filterCount", // 선택된 필터 개수 반영
                        color = NeutralWhite,
                        style = ourMenuTypography().pretendard_700_16
                    )
                }
            }

            LazyColumn(
                modifier = Modifier,
            ) {
                items(menuCount) { index ->
                    MenuFolderMenuButton(
                        menuFolderDetail = menus[index],
                        onMenuClick = {
//                            navController.navigate(route = Routes.MenuInfo)
                        },
                        onMapClick = {
//                            navController.navigate(route = Routes.MenuInfoMap)
                        }
                    )
                }

                item {
                    AddButton(
                        stringResource(R.string.add_menu),
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp)
                    ) {
//                        navController.navigate(route = Routes.AddMenu)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuFolderAllMenuScreenPreview() {
//    val navController = rememberNavController()
//
//    MenuFolderAllMenuScreen(navController)
}