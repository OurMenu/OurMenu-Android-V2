package com.kuit.ourmenu.ui.home.component.recommendation.sub

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.data.model.home.response.RecommendMenuList

@Composable
fun HomeSubRecommendation(
    modifier: Modifier = Modifier,
    imgUrl: String = "",
    homeSubDataList: List<RecommendMenuList>,
    onItemClick: (Long) -> Unit
) {
    val state = rememberLazyListState() // TODO : hoisting
    Column(modifier = modifier) {
        HomeSubRecommendationText(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 12.dp)
                .height(32.dp),
            imgUrl = imgUrl
        )

        HomeSubRecommendationList(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            state = state,
            homeSubDataList = homeSubDataList,
            onItemClick = onItemClick
        )
    }
}
