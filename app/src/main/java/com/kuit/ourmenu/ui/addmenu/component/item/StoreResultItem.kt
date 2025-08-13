package com.kuit.ourmenu.ui.addmenu.component.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.data.model.map.response.CrawlingStoreDetailResponse
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun StoreSearchResultItem(
    resultItem: CrawlingStoreDetailResponse,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 20.dp, horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_fill_map_20),
            contentDescription = "location info",
            tint = Color.Unspecified
        )
        Column(modifier = Modifier.padding(start = 20.dp)) {
            Text(
                text = resultItem.storeTitle,
                style = ourMenuTypography().pretendard_600_16,
            )
            Text(
                text = resultItem.storeAddress,
                style = ourMenuTypography().pretendard_500_14,
                color = Neutral700
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RestaurantSearchItemPreview() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        StoreSearchResultItem(
            resultItem = CrawlingStoreDetailResponse(
                storeTitle = stringResource(R.string.our_ddeokbokki),
                storeAddress = stringResource(R.string.resaturant_address),
                storeId = TODO(),
                storeImgs = TODO(),
                menus = TODO(),
                storeMapX = TODO(),
                storeMapY = TODO(),
            )
        )
    }
}
