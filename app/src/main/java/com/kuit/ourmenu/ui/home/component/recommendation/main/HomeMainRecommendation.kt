package com.kuit.ourmenu.ui.home.component.recommendation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.data.model.home.response.RecommendMenuList

@Composable
fun HomeMainRecommendation(
    modifier: Modifier = Modifier,
    homeMainDataList: List<RecommendMenuList>
) {
    Column(
        modifier = modifier
    ) {
        HomeMainRecommendationText(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .width(278.dp)
                .height(144.dp)
        )

        HomeMainRecommendationList(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .height(244.dp),
            homeMainDataList = homeMainDataList
        )
    }
}
