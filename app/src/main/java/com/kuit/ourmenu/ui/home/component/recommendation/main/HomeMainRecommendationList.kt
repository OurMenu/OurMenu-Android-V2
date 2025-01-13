package com.kuit.ourmenu.ui.home.component.recommendation.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeMainRecommendationList(
    modifier: Modifier = Modifier
) {
    val state = rememberLazyListState() // TODO : hoisting

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 28.dp),
        flingBehavior = rememberSnapFlingBehavior(lazyListState = state), // Snap Fling Behavior
        state = state
    ) {
        items(5) {
            HomeMainRecommendationItem()
        }
    }
}