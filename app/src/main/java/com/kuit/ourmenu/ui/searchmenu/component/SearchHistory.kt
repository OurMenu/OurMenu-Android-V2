package com.kuit.ourmenu.ui.searchmenu.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.data.model.map.response.MapSearchHistoryResponse
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun SearchHistoryList(
    modifier: Modifier = Modifier,
    historyList: List<MapSearchHistoryResponse>?,
    onClick: () -> Unit = {},
) {
    val lazyListState = rememberLazyListState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 68.dp)
    ) {
        if (historyList == null || historyList.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 68.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_addmenu_noresult),
                    contentDescription = "no result",
                    tint = Color.Unspecified
                )
                Text(
                    text = stringResource(R.string.no_result),
                    style = ourMenuTypography().pretendard_600_14,
                    color = Neutral500,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        } else {
            Text(
                text = "최근 검색",
                style = ourMenuTypography().pretendard_600_16.copy(
                    lineHeight = 20.sp,
                    color = Neutral700
                ),
                modifier = modifier
                    .padding(bottom = 4.dp)
                    .padding(horizontal = 28.dp)
            )
            LazyColumn(
                state = lazyListState
            ) {

                items(historyList.size) { index ->
                    SearchHistoryItem(
                        historyData = historyList[index],
                        onClick = onClick
                    )

                    if (index != historyList.size - 1) {
                        HorizontalDivider(
                            thickness = 1.dp,
                            color = Neutral300,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SearchHistoryItem(
    modifier: Modifier = Modifier,
    historyData: MapSearchHistoryResponse,
    onClick: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 20.dp, horizontal = 28.dp)
    ) {
        Text(
            text = historyData.menuTitle,
            style = ourMenuTypography().pretendard_600_16.copy(
                lineHeight = 20.sp,
                color = Neutral700
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_fill_map_20),
                contentDescription = "location info",
                tint = Color.Unspecified
            )
            Column(
                modifier = Modifier.padding(start = 4.dp)
            ) {
                Text(
                    text = historyData.storeTitle,
                    style = ourMenuTypography().pretendard_600_14.copy(
                        lineHeight = 20.sp,
                        color = Neutral500
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .height(20.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
                Text(
                    text = historyData.storeAddress,
                    style = ourMenuTypography().pretendard_600_14.copy(
                        lineHeight = 20.sp,
                        color = Neutral500
                    ),
                    modifier = Modifier
                        .height(20.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically)
                        .padding(top = 2.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchHistoryPreview() {
    SearchHistoryList(
        historyList = listOf(
            MapSearchHistoryResponse(
                menuTitle = "피자",
                storeTitle = "피자헛",
                menuId = 1,
                storeAddress = "서울특별시 강남구 역삼동 123-4"
            ),
            MapSearchHistoryResponse(
                menuTitle = "치킨",
                storeTitle = "굽네치킨",
                menuId = 2,
                storeAddress = "서울특별시 강남구 역삼동 456-7"
            ),
            MapSearchHistoryResponse(
                menuTitle = "햄버거",
                storeTitle = "맥도날드",
                menuId = 3,
                storeAddress = "서울특별시 강남구 역삼동 987-6"
            )
        ),
    )
}