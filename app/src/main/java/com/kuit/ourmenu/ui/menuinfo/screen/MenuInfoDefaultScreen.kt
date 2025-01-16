package com.kuit.ourmenu.ui.menuinfo.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kuit.ourmenu.ui.menuinfo.component.MenuInfoImagePager
import com.kuit.ourmenu.ui.menuinfo.component.MenuInfoTopIcons
import com.kuit.ourmenu.ui.menuinfo.dummy.MenuInfoDummyData

@Composable
fun MenuInfoDefaultScreen() {

    val pagerState = rememberPagerState { 3 }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        MenuInfoImagePager(
            pagerState = pagerState,
            pageItems = MenuInfoDummyData.dummyPageDataList
        )

        MenuInfoTopIcons(
            onBackClick = {},
            onVertClick = {}
        )


    }
}


@Preview
@Composable
private fun MenuInfoDefaultPreview() {
    MenuInfoDefaultScreen()
}