package com.kuit.ourmenu.ui.home.component.recommendation.sub

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.data.model.home.response.RecommendMenuList

@Composable
fun HomeSubRecommendationList(
    modifier: Modifier = Modifier,
    state: LazyListState,
    homeSubDataList: List<RecommendMenuList>,
    onItemClick: (Long) -> Unit
) {
    LazyRow(
        modifier = modifier,
        state = state,
        contentPadding = PaddingValues(
            start = 20.dp, end = 9.dp
        ),
    ) {
        items(homeSubDataList.size) { data ->
            HomeSubRecommendationItem(
                recommendData = homeSubDataList[data],
                onItemClick = onItemClick
            )
        }
    }
}