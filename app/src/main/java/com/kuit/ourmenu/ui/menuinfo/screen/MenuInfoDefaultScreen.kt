package com.kuit.ourmenu.ui.menuinfo.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.ui.menuinfo.component.info.MenuInfoAdditionalContent
import com.kuit.ourmenu.ui.menuinfo.component.info.MenuInfoChipContent
import com.kuit.ourmenu.ui.menuinfo.component.info.MenuInfoContent
import com.kuit.ourmenu.ui.menuinfo.component.info.MenuInfoImagePager
import com.kuit.ourmenu.ui.menuinfo.component.info.MenuInfoMapButton
import com.kuit.ourmenu.ui.menuinfo.component.info.MenuInfoTopIcons
import com.kuit.ourmenu.ui.menuinfo.dummy.MenuInfoDummyData
import com.kuit.ourmenu.ui.theme.Neutral300

@Composable
fun MenuInfoDefaultScreen() {

    val pagerState = rememberPagerState { 3 }

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box {
                MenuInfoImagePager(
                    pagerState = pagerState,
                    pageItems = MenuInfoDummyData.dummyData
                )
                MenuInfoTopIcons(
                    onBackClick = { },
                    onVertClick = { }
                )
            }

            MenuInfoContent(
                MenuInfoDummyData.dummyData
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                color = Neutral300
            )

            MenuInfoChipContent(
                menuInfoData = MenuInfoDummyData.dummyData
            )

            MenuInfoAdditionalContent(
                address = MenuInfoDummyData.dummyData.address,
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
}


@Preview(showBackground = true)
@Composable
private fun MenuInfoDefaultPreview() {
    MenuInfoDefaultScreen()
}