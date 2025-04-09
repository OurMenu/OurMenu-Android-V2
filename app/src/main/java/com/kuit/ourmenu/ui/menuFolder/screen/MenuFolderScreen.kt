package com.kuit.ourmenu.ui.menuFolder.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.menuFolder.component.AddButton
import com.kuit.ourmenu.ui.menuFolder.component.MenuFolderButton
import com.kuit.ourmenu.ui.menuFolder.component.MenuFolderTopAppBar
import com.kuit.ourmenu.ui.menuFolder.viewmodel.MenuFolderViewModel
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun MenuFolderScreen(
    onNavigateToDetail: (Int) -> Unit,
    onNavigateToAllMenu: () -> Unit,
    viewModel: MenuFolderViewModel = hiltViewModel()
) {
    // 현재 스와이프된 버튼의 인덱스를 관리 (한 번에 하나만 스와이프되도록)
    var swipedIndex by remember { mutableIntStateOf(-1) }

    val menuFolders by viewModel.menuFolders.collectAsStateWithLifecycle()
    val totalMenuCount by viewModel.menuCount.collectAsStateWithLifecycle()

    Log.d("MenuFolderScreen", "menuFolders: $menuFolders")

    Scaffold(
        topBar = {
            MenuFolderTopAppBar(
                onClick = {
//                    navController.navigate(route = Routes.AddMenu)
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .height(64.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Primary500Main)
                        .clickable(onClick = {
                            onNavigateToAllMenu()
                        }),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.see_all_menu),
                        color = NeutralWhite,
                        style = ourMenuTypography().pretendard_700_18
                    )

                    Text(
                        text = String.format(stringResource(R.string.menu_count), totalMenuCount),
                        color = NeutralWhite,
                        style = ourMenuTypography().pretendard_500_14,
                    )
                }
            }

            // TODO: 드래그 앤 드롭 구현
            itemsIndexed(menuFolders) { index, folder ->
                MenuFolderButton(
                    menuFolder = folder,
                    isSwiped = swipedIndex == index,
                    onSwipe = { swipedIndex = index },
                    onReset = { if (swipedIndex == index) swipedIndex = -1 },
                    onButtonClick = {
                        onNavigateToDetail(folder.menuFolderId)
                    }
                )
            }

            item {
                AddButton(
                    stringResource(R.string.add_menu_folder),
                    modifier = Modifier
                ) {
                    // TODO: 버튼 누르면 메뉴판 추가 페이지로 이동
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuFolderScreenPreview() {
    MenuFolderScreen(
        onNavigateToDetail = {},
        onNavigateToAllMenu = {},
    )
}