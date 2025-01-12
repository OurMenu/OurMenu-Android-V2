package com.kuit.ourmenu.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R

@Composable
fun MainRecommendationText(
    modifier: Modifier = Modifier,
    imgUrl: String = ""
) {
    Image(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .width(278.dp)
            .height(144.dp),
        painter = painterResource(id = R.drawable.ic_home_reco_1),
        contentDescription = "Home Banner"
    )
}

@Preview
@Composable
private fun MainRecommendationTextPreview() {
    MainRecommendationText()
}