package com.kuit.ourmenu.ui.addmenu.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.BottomFullWidthButton
import com.kuit.ourmenu.ui.theme.Neutral100
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral400
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.NeutralWhite
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMenuBottomSheetContent(scaffoldState: BottomSheetScaffoldState) {
    val coroutineScope = rememberCoroutineScope()
    val dummyMenuItemList = listOf(
        false, false, true, false, false,false, false, false
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
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
        //BottomSheet가 확장된 상태가 아닐 때 (AddMenuScreen에서) 보여줄 버튼
        if (scaffoldState.bottomSheetState.targetValue != SheetValue.Expanded) {
            HorizontalDivider(
                thickness = 1.dp,
                color = Neutral300,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            )
            BottomFullWidthButton(
                containerColor = Neutral400,
                contentColor = NeutralWhite,
                text = stringResource(R.string.next)
            ) {
                coroutineScope.launch {
                    scaffoldState.bottomSheetState.expand()
                }
            }
        }else{
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 12.dp)
            ) {
                items(dummyMenuItemList) { items ->
                    SelectMenuItem(items)
                }
            }

            Column() {
                BottomFullWidthButton(
                    containerColor = Neutral100,
                    contentColor = Neutral500,
                    text = stringResource(R.string.add_menu_by_my_own)
                ) {

                }
                Spacer(modifier = Modifier.padding(10.dp))
                BottomFullWidthButton(
                    containerColor = Neutral400,
                    contentColor = NeutralWhite,
                    text = stringResource(R.string.next)
                ) {

                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun AddMenuBottomSheetContentPreview() {
    val scaffoldState = rememberBottomSheetScaffoldState()
    LaunchedEffect(Unit){
        //아래 주석 해제하면 bottom sheet 확장된 상태 확인 가능
//       scaffoldState.bottomSheetState.expand()
    }
    AddMenuBottomSheetContent(scaffoldState)
}