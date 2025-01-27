package com.kuit.ourmenu.ui.addmenu.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.addmenu.component.bottomsheet.IconSelectBottomSheet
import com.kuit.ourmenu.ui.addmenu.component.bottomsheet.TagSelectBottomSheet
import com.kuit.ourmenu.ui.common.topappbar.OurMenuBackButtonTopAppBar
import com.kuit.ourmenu.ui.common.CustomTextField
import com.kuit.ourmenu.ui.theme.Neutral100
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMenuTagScreen(modifier: Modifier = Modifier) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val showBottomSheet by remember { mutableStateOf(true) }
    val useTagBottomSheet by rememberSaveable { mutableStateOf(false) }
    var memoTitle by rememberSaveable { mutableStateOf("") }
    var memoBody by rememberSaveable { mutableStateOf("") }
    val scrollState = rememberScrollState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            OurMenuBackButtonTopAppBar {
                Text(
                    text = stringResource(R.string.add_menu),
                    style = ourMenuTypography().pretendard_600_18,
                    color = Neutral900,
                )
            }
        },
        sheetContainerColor = Color.White,
        sheetContent = {
            if (useTagBottomSheet) {
                //음색 태그 선택하는 bottom sheet
                TagSelectBottomSheet()
            } else {
                //아이콘 선택하는 bottom sheet
                IconSelectBottomSheet()
            }
        },
        //태그 고르기 눌러야 보이도록
        sheetPeekHeight = if (showBottomSheet) 254.dp else 0.dp,
        sheetDragHandle = {
            // 커스텀 핸들
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .height(4.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Neutral300)
                )
            }
        }
    ) {
        //전체 화면 구성
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            //태그
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 28.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.tag),
                    style = ourMenuTypography().pretendard_600_14
                )
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Primary500Main),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary500Main,
                        contentColor = NeutralWhite
                    ),
                    modifier = Modifier.size(128.dp, 32.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_filter_white_16),
                            contentDescription = "select tag",
                            tint = Color.Unspecified
                        )
                        Text(
                            text = stringResource(R.string.choose_tag),
                            style = ourMenuTypography().pretendard_700_16,
                            color = NeutralWhite
                        )
                    }
                }
            }

            //메모
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.memo),
                style = ourMenuTypography().pretendard_600_14
            )
            Spacer(modifier = Modifier.height(4.dp))
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .border(1.dp, Neutral300, RoundedCornerShape(8.dp)),
                text = memoTitle,
                onTextChange = { memoTitle = it },
                shape = RoundedCornerShape(8.dp),
                paddingValues = PaddingValues(28.dp, 12.dp),
                containerColor = Neutral100,
                placeHolder = {
                    Text(
                        text = stringResource(R.string.type_title),
                        style = ourMenuTypography().pretendard_500_14,
                        color = Neutral500
                    )
                },
                textStyle = ourMenuTypography().pretendard_500_14.copy(color = Neutral700)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(88.dp)
                    .background(Neutral100)
                    .border(1.dp, Neutral300, RoundedCornerShape(8.dp))
                    .verticalScroll(scrollState),
            ) {
                CustomTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(88.dp),
                    text = memoBody,
                    singleLine = false,
                    onTextChange = { memoBody = it },
                    shape = RoundedCornerShape(8.dp),
                    paddingValues = PaddingValues(28.dp, 12.dp),
                    containerColor = Neutral100,
                    placeHolder = {
                        Text(
                            text = stringResource(R.string.type_body),
                            style = ourMenuTypography().pretendard_500_12,
                            color = Neutral500,
                        )
                    },
                    textStyle = ourMenuTypography().pretendard_500_14.copy(color = Neutral700)
                )
            }

            //아이콘
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(R.string.icon),
                    style = ourMenuTypography().pretendard_600_14
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddMenuTagScreenPreview() {
    AddMenuTagScreen()
}