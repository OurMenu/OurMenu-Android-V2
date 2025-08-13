package com.kuit.ourmenu.ui.home.component.recommendation.sub

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.kuit.ourmenu.R
import com.kuit.ourmenu.data.model.home.response.RecommendMenuList
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.ourMenuTypography


@Composable
fun HomeSubRecommendationItem(
    recommendData: RecommendMenuList,
    onItemClick: (Long) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(end = 11.dp)
            .clickable { onItemClick(recommendData.menuId) }
    ) {
        AsyncImage(
            model = recommendData.menuImgUrl,
            contentDescription = "Home Sub Recommendation Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(140.dp)
                .width(180.dp)
                .padding(bottom = 8.dp)
                .clip(shape = RoundedCornerShape(12.dp))
        )
        Text(
            text = recommendData.menuTitle,
            style = ourMenuTypography().pretendard_600_18.copy(
                color = Neutral900,
                lineHeight = 27.sp
            )
        )
        Text(
            text = recommendData.storeName,
            style = TextStyle(
                // TODO : Design system 적용
                fontSize = 14.sp,
                lineHeight = 21.sp,
                fontFamily = FontFamily(Font(R.font.medium)),
                fontWeight = FontWeight(500),
                color = Neutral700,
                letterSpacing = (-0.154).sp
            )
        )
    }
}
