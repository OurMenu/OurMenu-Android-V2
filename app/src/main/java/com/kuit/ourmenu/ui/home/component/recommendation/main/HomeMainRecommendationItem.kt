package com.kuit.ourmenu.ui.home.component.recommendation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun HomeMainRecommendationItem() {
    Box(
        modifier = Modifier
            .height(244.dp)
            .width(304.dp)
            .padding(horizontal = 6.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_dummy_pizza), // TODO : 추후 Async Image Loading 적용
            contentDescription = "Main Recommendation Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(244.dp)
                .width(304.dp)
                .clip(RoundedCornerShape(32.dp))
        )

        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                contentAlignment = Alignment.CenterStart, // 수직 및 수평 중앙 정렬
            ) {
                Text(
                    text = "화산라멘",
                    style = ourMenuTypography().pretendard_700_24.copy(
                        shadow = Shadow(
                            color = Color.Black.copy(alpha = 0.2f), // 그림자 색상 및 투명도
                            offset = Offset(0f, 2f), // 그림자 오프셋 (x = 0px, y = 2px)
                            blurRadius = 4f // 블러 반경
                        )
                    ),
                    color = NeutralWhite,
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .height(24.dp),
                contentAlignment = Alignment.CenterStart, // 수직 및 수평 중앙 정렬
            ) {
                Text(
                    text = "화산라멘 멘야마쯔리 홍대점",
                    style = ourMenuTypography().pretendard_600_16.copy(
                        shadow = Shadow(
                            color = Color.Black.copy(alpha = 0.2f), // 그림자 색상 및 투명도
                            offset = Offset(0f, 2f), // 그림자 오프셋 (x = 0px, y = 2px)
                            blurRadius = 4f // 블러 반경
                        ),
                        letterSpacing = (-0.4).sp,
                        fontStyle = FontStyle.Normal,
                    ),
                    color = NeutralWhite,
                )
            }
        }
    }
}