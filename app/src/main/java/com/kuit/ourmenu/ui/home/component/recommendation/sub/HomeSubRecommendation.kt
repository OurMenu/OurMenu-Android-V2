package com.kuit.ourmenu.ui.home.component.recommendation.sub

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.home.dummy.HomeDummyData
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun HomeSubRecommendation(
    modifier: Modifier = Modifier,
    homeSubDataList: List<HomeDummyData> = listOf()
) {
    val state = rememberLazyListState() // TODO : hoisting

    Column(
        modifier = modifier
    ) {
        HomeSubRecommendationText(
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

@Preview(showBackground = true)
@Composable
private fun HomeSubRecommendationListPreview() {
    HomeSubRecommendation(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        HomeDummyData.dummyData
    )
}
