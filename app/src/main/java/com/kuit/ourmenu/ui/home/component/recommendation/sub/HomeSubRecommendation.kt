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
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun HomeSubRecommendation(
    homeSubDataList: List<HomeSubDummyData> = listOf()
) {
    val state = rememberLazyListState() // TODO : hoisting

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
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
        listOf(
            HomeSubDummyData(
                imageRes = R.drawable.img_dummy_pizza,
                name = "초코 소프트콘",
                store = "아이스크림세계할인점",
            ),
            HomeSubDummyData(
                imageRes = R.drawable.img_dummy_pizza,
                name = "초코 소프트콘",
                store = "아이스크림세계할인점",
            ),
            HomeSubDummyData(
                imageRes = R.drawable.img_dummy_pizza,
                name = "초코 소프트콘",
                store = "아이스크림세계할인점",
            ),
        )
    )
}

data class HomeSubDummyData(
    val imageRes: Int,
    val name: String,
    val store: String,
)