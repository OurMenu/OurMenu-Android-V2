package com.kuit.ourmenu.ui.addmenu.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.addmenu.component.AddMenuTopAppBar
import com.kuit.ourmenu.ui.addmenu.component.RestaurantSearchItem
import com.kuit.ourmenu.ui.common.BottomFullWidthButton
import com.kuit.ourmenu.ui.common.SearchBar
import com.kuit.ourmenu.ui.theme.Neutral100
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMenuSearchScreen(modifier: Modifier = Modifier) {
    val searchActionDone by rememberSaveable { mutableStateOf(true) }
    val dummyRecentSearchList = mutableListOf(
        false,
        false,
        false,
        false,
        true,
    )
    val dummySearchResultList: MutableList<Boolean> = mutableListOf()

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            AddMenuTopAppBar {
                Text(
                    text = stringResource(R.string.ourmenu),
                    color = Primary500Main,
                    style = ourMenuTypography().pretendard_600_18
                )
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                BottomFullWidthButton(
                    onClick = { TODO() },
                    containerColor = Neutral100,
                    contentColor = Neutral500,
                    text = stringResource(R.string.add_restaurant_and_menu_by_myself)
                )
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp, horizontal = 20.dp)
                ) {
                    SearchBar { TODO() }
                }
                Column(modifier = Modifier.fillMaxSize()) {
                    if (searchActionDone) {
                        //검색을 한 경우
                        if (dummySearchResultList.isEmpty()) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 64.dp),
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
                            LazyColumn {
                                items(dummySearchResultList) { item ->
                                    RestaurantSearchItem(item)
                                }
                            }
                        }
                    } else {
                        //검색을 하지 않은 경우
                        Text(
                            text = stringResource(R.string.recent_search),
                            style = ourMenuTypography().pretendard_600_14,
                            color = Neutral700,
                            modifier = Modifier.padding(start = 28.dp)
                        )
                        if (dummyRecentSearchList.isEmpty()) {
                            Column(modifier = Modifier.fillMaxSize()) { /*empty view*/ }
                        } else {
                            LazyColumn {
                                items(dummyRecentSearchList) { item ->
                                    RestaurantSearchItem(item)
                                }
                            }
                        }
                    }

                }
            }
        }
    )

}

@Preview(showBackground = true)
@Composable
private fun AddMenuSearchScreenPreview() {
    AddMenuSearchScreen()
}