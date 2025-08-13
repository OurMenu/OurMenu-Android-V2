package com.kuit.ourmenu.ui.menuinfo.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kuit.ourmenu.ui.common.topappbar.BackButtonTopAppBar
import com.kuit.ourmenu.ui.menuinfo.component.info.MenuInfoAdditionalContent
import com.kuit.ourmenu.ui.menuinfo.component.info.MenuInfoChipContent
import com.kuit.ourmenu.ui.menuinfo.component.info.MenuInfoContent
import com.kuit.ourmenu.ui.menuinfo.component.info.MenuInfoImagePager
import com.kuit.ourmenu.ui.menuinfo.component.info.MenuInfoMapButton
import com.kuit.ourmenu.ui.menuinfo.viewmodel.MenuInfoViewModel
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.NeutralWhite

@Composable
fun MenuInfoDefaultScreen(
    menuId: Long,
    onNavigateBack: () -> Unit,
    onNavigateToMenuFolderDetail: (Long) -> Unit,
//    onNavigateToMap: () -> Unit,
    viewModel: MenuInfoViewModel = hiltViewModel()
) {
    LaunchedEffect(menuId) {
        viewModel.getMenuInfo(menuId)
    }

    val menuInfo by viewModel.menuInfo.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(
        pageCount = { menuInfo.menuImgUrls.size.coerceAtLeast(1) } // 최소 1
    )

    Scaffold(
        topBar = {},
        content = { innerPadding ->
            Box {
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    Box {
                        MenuInfoImagePager(
                            pagerState = pagerState,
                            imgUrls = menuInfo.menuImgUrls
                        )
                    }

                    MenuInfoContent(
                        menuInfoData = menuInfo
                    )

                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        color = Neutral300
                    )

                    MenuInfoChipContent(
                        // TODO: 메뉴 폴더 정보에 따라 변경 필요, 여러개인 경우 각 폴더에 대한 이동 구현
//                        onNavigateToMenuFolderDetail = onNavigateToMenuFolderDetail(menuInfo.menuFolders.),
                        menuInfoData = menuInfo
                    )

                    MenuInfoAdditionalContent(
                        address = menuInfo.storeAddress,
                        memoTitle = menuInfo.menuMemoTitle,
                        memoContent = menuInfo.menuMemoContent
                    )
                }
                MenuInfoMapButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 20.dp, bottom = 28.dp),
                ) { }
            }

            BackButtonTopAppBar(NeutralWhite, true) {
                onNavigateBack()
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
private fun MenuInfoDefaultPreview() {
//    val navController = rememberNavController()
//
//    MenuInfoDefaultScreen(navController)
    val viewModel: MenuInfoViewModel = hiltViewModel()
    MenuInfoDefaultScreen(
        menuId = 1,
        onNavigateBack = {},
        onNavigateToMenuFolderDetail = {},
        viewModel = viewModel
    )
}