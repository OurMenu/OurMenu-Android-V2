package com.kuit.ourmenu.ui.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.home.component.HomeTopAppBar
import com.kuit.ourmenu.ui.home.component.MainRecommendationList
import com.kuit.ourmenu.ui.home.component.MainRecommendationText

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            HomeTopAppBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.Start
        ) {

            Spacer(modifier = Modifier.size(26.dp))

            MainRecommendationText()

            Spacer(modifier = Modifier.size(32.dp))

            MainRecommendationList()

        }
    }
}

@Preview(
    widthDp = 360,
    heightDp = 800
)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}