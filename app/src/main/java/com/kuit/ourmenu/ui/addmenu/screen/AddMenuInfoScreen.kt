package com.kuit.ourmenu.ui.addmenu.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.addmenu.component.AddMenuAddImageComponent
import com.kuit.ourmenu.ui.addmenu.component.item.AddMenuInfoAddressFieldItem
import com.kuit.ourmenu.ui.addmenu.component.item.AddMenuInfoMenuBoardFieldItem
import com.kuit.ourmenu.ui.addmenu.component.item.AddMenuInfoTextFieldItem
import com.kuit.ourmenu.ui.common.BottomFullWidthButton
import com.kuit.ourmenu.ui.common.topappbar.OurMenuBackButtonTopAppBar
import com.kuit.ourmenu.ui.theme.Neutral400
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
    var imgList by rememberSaveable {
        mutableStateOf(
            listOf(
                R.drawable.img_dummy_pizza,
                R.drawable.img_dummy_pizza,
                R.drawable.img_dummy_pizza,
                R.drawable.img_dummy_pizza,
            )
        )
    }

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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                ) {
                    AddMenuAddImageComponent(imgList = imgList) { index ->
                        imgList = imgList.toMutableList().apply {
                            removeAt(index)
                        }
                    }
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