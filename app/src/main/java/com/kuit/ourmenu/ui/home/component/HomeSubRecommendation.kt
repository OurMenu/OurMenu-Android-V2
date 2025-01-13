package com.kuit.ourmenu.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.OurMenuTheme
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun HomeSubRecommendation(
    homeSubDataList: List<HomeSubDummyData>
) {
    val state = rememberLazyListState() // TODO : hoisting

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        HomeSubRecommendationText(
            icon = R.drawable.ic_home_sub_reco_1,
            text = "추천 메뉴"
        )

        HomeSubRecommendationList(
            state = state,
            homeSubDataList = homeSubDataList
        )
    }
}


@Composable
fun HomeSubRecommendationText(
    modifier: Modifier = Modifier,
    icon: Int,
    text: String,
) {
    Row(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 11.dp),
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

@Preview(showBackground = true)
@Composable
private fun HomeSubRecommendationListPreview() {
    HomeSubRecommendation(
        listOf(
            HomeSubDummyData(
                imageRes = R.drawable.img_dummy_pizza,
                name = "초코 소프트콘",
                store = "아이스크림세계할인점",
            ),
            HomeSubDummyData(
                imageRes = R.drawable.img_dummy_pizza,
                name = "초코 소프트콘",
                store = "아이스크림세계할인점",
            ),
            HomeSubDummyData(
                imageRes = R.drawable.img_dummy_pizza,
                name = "초코 소프트콘",
                store = "아이스크림세계할인점",
            ),
        )
    )
}

data class HomeSubDummyData(
    val imageRes: Int,
    val name: String,
    val store: String,
)