package com.kuit.ourmenu.ui.menuinfo.component.info

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.ui.menuinfo.dummy.MenuInfoDummyData
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.SpringIndicatorType

@Composable
fun MenuInfoImagePager(
    pagerState: PagerState,
    pageItems: MenuInfoDummyData
) {
    val pagerCount = pageItems.imgRes.size

    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .height(292.dp),
            state = pagerState
        ) { index ->
            Image(
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(pageItems.imgRes[index])
            )
        }
        DotsIndicator(
            modifier = Modifier
                .padding(bottom = 12.dp),
            dotCount = pagerCount,
            type = SpringIndicatorType(
                dotsGraphic = DotGraphic(
                    4.dp,
                    color = Neutral500
                ),
                selectorDotGraphic = DotGraphic(
                    4.dp,
                    color = NeutralWhite
                )
            ),
            pagerState = pagerState,
            dotSpacing = 6.dp
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun MenuInfoPreview() {
    val state = rememberPagerState(pageCount = { 3 })

    MenuInfoImagePager(
        pagerState = state,
        pageItems = MenuInfoDummyData.dummyData
    )
}