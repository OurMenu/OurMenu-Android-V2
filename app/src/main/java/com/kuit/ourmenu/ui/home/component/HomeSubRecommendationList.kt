package com.kuit.ourmenu.ui.home.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeSubRecommendationList(
    modifier: Modifier = Modifier,
    state: LazyListState,
    homeSubDataList: List<HomeSubDummyData>
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        state = state,
        contentPadding = PaddingValues(
            start = 20.dp, end = 9.dp
        ),
    ) {
        items(homeSubDataList.size) { data ->
            HomeSubRecommendationItem(
                recommendData = homeSubDataList[data]
            )
        }
    }
}