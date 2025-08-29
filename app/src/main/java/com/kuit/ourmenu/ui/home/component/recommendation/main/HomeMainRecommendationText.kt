package com.kuit.ourmenu.ui.home.component.recommendation.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage

@Composable
fun HomeMainRecommendationText(
    modifier: Modifier = Modifier,
    imgUrl: String = ""
) {
    AsyncImage(
        modifier = modifier,
        model = imgUrl,
        contentDescription = "Home Banner",
    )
}

