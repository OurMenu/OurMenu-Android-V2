package com.kuit.ourmenu.ui.addmenu.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuit.ourmenu.R

@Composable
fun AddMenuBottomSheetContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Text(text = "식당 이름", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_location_info),
                contentDescription = "location info icon",
                //이 코드를 없애면 아이콘이 검은색으로 보여서 작성
                tint = Color.Unspecified
            )
            Text(
                text = "주소에 해당하는 텍스트",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            //이미지 3개 배치
            Image(
                painter = painterResource(id = R.drawable.img_dummy_pizza),
                contentDescription = "dummy data",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(104.dp)
                    .height(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Image(
                painter = painterResource(id = R.drawable.img_dummy_pizza),
                contentDescription = "dummy data",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(104.dp)
                    .height(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Image(
                painter = painterResource(id = R.drawable.img_dummy_pizza),
                contentDescription = "dummy data",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(104.dp)
                    .height(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)) {
            SelectMenuItem(false)
            SelectMenuItem(true)
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun AddMenuBottomSheetContentPreview() {
    AddMenuBottomSheetContent()
}