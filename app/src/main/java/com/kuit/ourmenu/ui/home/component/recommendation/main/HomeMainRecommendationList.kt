package com.kuit.ourmenu.ui.home.component.recommendation.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.ui.home.dummy.HomeDummyData

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeMainRecommendationList(
    modifier: Modifier = Modifier,
    homeMainDataList: List<HomeDummyData>
) {
    val state = rememberLazyListState() // TODO : hoisting
    val startIndex = (Int.MAX_VALUE / 2) - (Int.MAX_VALUE / 2) % homeMainDataList.size
    LaunchedEffect(Unit) {
        state.scrollToItem(startIndex)
    }

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 28.dp),
        flingBehavior = rememberSnapFlingBehavior(lazyListState = state), // Snap Fling Behavior
        state = state
    ) {
        items(Int.MAX_VALUE) { index ->
            val itemIndex = index % homeMainDataList.size

            HomeMainRecommendationItem(
                modifier = Modifier
                    .height(244.dp)
                    .width(304.dp)
                    .padding(horizontal = 6.dp),
                recommendData = homeMainDataList[itemIndex]
            )
        }
    }
}