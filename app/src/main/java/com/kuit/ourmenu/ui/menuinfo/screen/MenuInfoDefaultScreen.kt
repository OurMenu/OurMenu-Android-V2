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
import com.kuit.ourmenu.ui.menuinfo.dummy.MenuInfoDummyData
import com.kuit.ourmenu.ui.menuinfo.viewmodel.MenuInfoViewModel
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.NeutralWhite

@Composable
fun MenuInfoDefaultScreen(
    menuId: Int,
    onNavigateBack: () -> Unit,
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
                        menuInfoData = menuInfo
                    )

                    MenuInfoAdditionalContent(
                        address = menuInfo.storeAddress,
                        // TODO: 메뉴 정보에 따라 변경 필요
                        memoTitle = MenuInfoDummyData.dummyData.memoTitle,
                        memoContent = MenuInfoDummyData.dummyData.memoContent
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
}