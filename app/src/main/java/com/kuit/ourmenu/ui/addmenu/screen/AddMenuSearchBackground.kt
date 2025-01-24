package com.kuit.ourmenu.ui.addmenu.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
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
import com.kuit.ourmenu.ui.addmenu.component.item.RestaurantSearchItem
import com.kuit.ourmenu.ui.common.BottomFullWidthButton
import com.kuit.ourmenu.ui.theme.Neutral100
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun AddMenuSearchBackground(
    modifier: Modifier = Modifier,
    searchActionDone: Boolean,
    recentSearchResults: MutableList<Boolean>, //이후에 타입 변경
    searchResults: MutableList<Boolean> //이후에 타입 변경
) {

//    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
//        },
//        bottomBar = {
//            //Button
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 20.dp),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
//            ) {
//                BottomFullWidthButton(
//                    onClick = { TODO() },
//                    containerColor = Neutral100,
//                    contentColor = Neutral500,
//                    text = stringResource(R.string.add_restaurant_and_menu_by_myself)
//                )
//            }
//        },
//        content = {
//            Column(
//                modifier = Modifier
//                    .padding(paddingValues)
//                    .fillMaxWidth(),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Column(modifier = Modifier.fillMaxSize()) {
//                    if (searchActionDone) {
//                        //검색을 한 경우
//                        if (searchResults.isEmpty()) {
//                            Column(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(top = 116.dp),
//                                horizontalAlignment = Alignment.CenterHorizontally
//                            ) {
//                                Icon(
//                                    painter = painterResource(R.drawable.ic_addmenu_noresult),
//                                    contentDescription = "no result",
//                                    tint = Color.Unspecified
//                                )
//                                Text(
//                                    text = stringResource(R.string.no_result),
//                                    style = ourMenuTypography().pretendard_600_14,
//                                    color = Neutral500,
//                                    modifier = Modifier.padding(top = 8.dp)
//                                )
//                            }
//                        } else {
//                            LazyColumn {
//                                items(searchResults) { item ->
//                                    RestaurantSearchItem(item)
//                                }
//                            }
//                        }
//                    } else {
//                        //검색을 하지 않은 경우
//                        Text(
//                            text = stringResource(R.string.recent_search),
//                            style = ourMenuTypography().pretendard_600_14,
//                            color = Neutral700,
//                            modifier = Modifier.padding(start = 28.dp, top = 12.dp)
//                        )
//                        if (recentSearchResults.isEmpty()) {
//                            Column(modifier = Modifier.fillMaxSize()) { /*empty view*/ }
//                        } else {
//                            LazyColumn {
//                                items(recentSearchResults) { item ->
//                                    RestaurantSearchItem(item)
//                                }
//                            }
//                        }
//                    }
//
//                }
//            }
//        }
//    )

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 52.dp)
        ) {
            if (searchActionDone) {
                //검색을 한 경우
                if (searchResults.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 116.dp),
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
                    LazyColumn(modifier = Modifier.padding(top = 68.dp)) {
                        items(searchResults) { item ->
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
                    modifier = Modifier.padding(start = 28.dp, top = 68.dp)
                )
                if (recentSearchResults.isEmpty()) {
                    Column(modifier = Modifier.fillMaxSize()) { /*empty view*/ }
                } else {
                    LazyColumn() {
                        items(recentSearchResults) { item ->
                            RestaurantSearchItem(item)
                        }
                    }
                }
            }

        }

        BottomFullWidthButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
            onClick = { TODO() },
            containerColor = Neutral100,
            contentColor = Neutral500,
            text = stringResource(R.string.add_restaurant_and_menu_by_myself)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AddMenuSearchBackgroundPreview() {
    val searchActionDone by rememberSaveable { mutableStateOf(false) }
    val recentSearchResults = mutableListOf(
        false,
        false,
        false,
        false,
        true,
    )
    val searchResults: MutableList<Boolean> = mutableListOf(true)
    AddMenuSearchBackground(
        searchActionDone = searchActionDone,
        recentSearchResults = recentSearchResults,
        searchResults = searchResults
    )
}