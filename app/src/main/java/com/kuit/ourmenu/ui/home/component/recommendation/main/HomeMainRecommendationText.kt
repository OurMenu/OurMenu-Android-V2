package com.kuit.ourmenu.ui.home.component.recommendation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R

@Composable
fun HomeMainRecommendationText(
    modifier: Modifier = Modifier,
    imgUrl: String = ""
) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_home_reco_1),
        contentDescription = "Home Banner"
    )
}

