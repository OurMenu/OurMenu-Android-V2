package com.kuit.ourmenu.ui.addmenu.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuit.ourmenu.R

@Composable
fun RestaurantSearchItem(isLastItem: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_location_info),
            contentDescription = "location info",
            tint = Color.Unspecified
        )
        Column(modifier = Modifier.padding(start = 20.dp)) {
            Text(text = stringResource(R.string.our_ddeokbokki), fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = stringResource(R.string.neungdongro_112), fontSize = 14.sp, color = Color.Gray)
        }
    }

    //마지막 item이 아닌경우에만 하단에 divider 표시
    if (!isLastItem) {
        HorizontalDivider(
            thickness = 1.dp,
            color = Color.LightGray,
            modifier = Modifier.fillMaxWidth()
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun RestaurantSearchItemPreview() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        RestaurantSearchItem()
        RestaurantSearchItem()
        RestaurantSearchItem(true)
    }
}