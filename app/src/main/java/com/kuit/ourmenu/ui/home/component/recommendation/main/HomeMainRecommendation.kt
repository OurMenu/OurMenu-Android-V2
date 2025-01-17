package com.kuit.ourmenu.ui.home.component.recommendation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.ui.home.dummy.HomeDummyData

@Composable
fun HomeMainRecommendation(
    modifier: Modifier = Modifier,
    homeMainDataList : List<HomeDummyData>
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

@Preview(showBackground = true)
@Composable
private fun MainRecommendationPreview() {
    HomeMainRecommendation(
        modifier = Modifier.padding(top = 16.dp),
        homeMainDataList = HomeDummyData.dummyData
    )
}
