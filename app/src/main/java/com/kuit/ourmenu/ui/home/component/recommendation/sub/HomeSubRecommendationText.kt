package com.kuit.ourmenu.ui.home.component.recommendation.sub

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.ourMenuTypography


@Composable
fun HomeSubRecommendationText(
    modifier: Modifier = Modifier,
    icon: Int,
    text: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // TODO : Async Image Loading
        Icon(
            painter = painterResource(icon),
            modifier = Modifier
                .size(32.dp)
                .padding(end = 4.dp),
            contentDescription = "Home Sub Recommendation 1",
            tint = Color.Unspecified
        )
        Text(
            text = text,
            style = ourMenuTypography().pretendard_700_24.copy(
                color = Neutral900
            )
        )
    }
}

