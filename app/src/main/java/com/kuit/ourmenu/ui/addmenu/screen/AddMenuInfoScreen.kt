package com.kuit.ourmenu.ui.addmenu.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.addmenu.component.item.AddMenuInfoAddressFieldItem
import com.kuit.ourmenu.ui.addmenu.component.item.AddMenuInfoMenuBoardFieldItem
import com.kuit.ourmenu.ui.addmenu.component.item.AddMenuInfoTextFieldItem
import com.kuit.ourmenu.ui.common.BottomFullWidthButton
import com.kuit.ourmenu.ui.common.topappbar.OurMenuBackButtonTopAppBar
import com.kuit.ourmenu.ui.theme.Neutral300
import com.kuit.ourmenu.ui.theme.Neutral400
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.ourMenuTypography


@Composable
fun AddMenuInfoScreen(autoInput: Boolean = true) {
    var menuBoardText by rememberSaveable { mutableStateOf("") }
    var menuNameText by rememberSaveable { mutableStateOf("") }
    var priceText by rememberSaveable { mutableStateOf("") }
    var storeNameText by rememberSaveable { mutableStateOf("") }
    var mainAddressText by rememberSaveable { mutableStateOf("") }
    var detailedAddressText by rememberSaveable { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            OurMenuBackButtonTopAppBar {
                Text(
                    text = stringResource(R.string.add_menu),
                    style = ourMenuTypography().pretendard_600_18
                )
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
            ) {
                BottomFullWidthButton(
                    containerColor = Neutral400,
                    contentColor = NeutralWhite,
                    text = stringResource(R.string.next)
                ) {
                    TODO()
                }
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(start = 20.dp, end = 20.dp)
                    .fillMaxWidth()
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.size(88.dp, 72.dp),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Neutral300
                        )
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_addmenu_add_photo),
                                contentDescription = "add photo",
                                tint = Color.Unspecified
                            )
                            Text(
                                text = "0/5",
                                style = ourMenuTypography().pretendard_500_12,
                                color = Neutral500
                            )
                        }
                    }
                    //메뉴 이미지 LazyRow 작성 예정

                }

                Column(modifier = Modifier.fillMaxWidth()) {
                    AddMenuInfoMenuBoardFieldItem(
                        text = menuBoardText,
                        onTextChange = { menuBoardText = it }
                    )

                    AddMenuInfoTextFieldItem(
                        fieldName = stringResource(R.string.menu_name),
                        autoInput = autoInput,
                        text = menuNameText,
                        onTextChange = { menuNameText = it },
                        placeholder = stringResource(R.string.type_menu_name)
                    )
                    AddMenuInfoTextFieldItem(
                        fieldName = stringResource(R.string.menu_price),
                        autoInput = autoInput,
                        text = priceText,
                        onTextChange = { priceText = it },
                        placeholder = stringResource(R.string.type_menu_price),
                        isPriceInfo = true
                    )
                    AddMenuInfoTextFieldItem(
                        fieldName = stringResource(R.string.store_name),
                        text = storeNameText,
                        autoInput = autoInput,
                        onTextChange = { storeNameText = it },
                        placeholder = stringResource(R.string.type_store_name)
                    )

                    AddMenuInfoAddressFieldItem(
                        autoInput = autoInput,
                        mainAddressText = mainAddressText,
                        onMainAddressChange = { mainAddressText = it },
                        detailedAddressText = detailedAddressText,
                        onDetailedAddressChange = { detailedAddressText = it }
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun AddMenuInfoScreenPreview() {
    //가게와 메뉴 직접 추가하기를 통해 해당 화면으로 오는 경우에는 인자로 false를 넘겨준다
//    AddMenuInfoScreen(true)
    AddMenuInfoScreen(false)
}