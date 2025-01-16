package com.kuit.ourmenu.ui.menuinfo.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType
import com.tbuonomo.viewpagerdotsindicator.compose.type.SpringIndicatorType

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MenuInfoImagePager(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    pagerCount: Int
) {

    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .height(292.dp),
            state = pagerState
        ) {
            // Image
            Image(
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.img_dummy_pizza)
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
        pagerCount = 3
    )
}