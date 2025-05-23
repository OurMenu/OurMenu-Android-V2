package com.kuit.ourmenu.ui.home.component.recommendation.sub

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.data.model.home.response.RecommendMenuList

@Composable
fun HomeSubRecommendation(
    modifier: Modifier = Modifier,
    homeSubDataList: List<RecommendMenuList>
) {
    val state = rememberLazyListState() // TODO : hoisting
    Column(modifier = modifier) {
        HomeSubRecommendationText( // TODO: 서버에서 데이터 받아오기
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 11.dp),
            icon = R.drawable.ic_home_sub_reco_1,
            text = "추천 메뉴"
        )

        HomeSubRecommendationList(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            state = state,
            homeSubDataList = homeSubDataList
        )
    }
}
