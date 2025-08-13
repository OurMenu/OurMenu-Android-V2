package com.kuit.ourmenu.ui.home.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.kuit.ourmenu.ui.common.topappbar.OurMenuAddButtonTopAppBar
import com.kuit.ourmenu.ui.home.component.recommendation.main.HomeMainRecommendation
import com.kuit.ourmenu.ui.home.component.recommendation.sub.HomeSubRecommendation
import com.kuit.ourmenu.ui.home.dummy.HomeDummyData

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            OurMenuAddButtonTopAppBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.Start
        ) {

            HomeMainRecommendation(
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 29.dp),
                homeMainDataList = HomeDummyData.dummyData
            )


            HomeSubRecommendation(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 25.dp),
                homeSubDataList = HomeDummyData.dummyData
            )

            HomeSubRecommendation(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 25.dp),
                homeSubDataList = HomeDummyData.dummyData
            )
        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 800
)
@Composable
private fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen()
}