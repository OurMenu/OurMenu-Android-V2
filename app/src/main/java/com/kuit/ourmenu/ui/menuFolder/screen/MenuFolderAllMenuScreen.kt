package com.kuit.ourmenu.ui.menuFolder.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.topappbar.BackButtonTopAppBar
import com.kuit.ourmenu.ui.menuFolder.component.MenuFolderMenuButton
import com.kuit.ourmenu.ui.theme.Neutral100
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun MenuFolderAllMenuScreen(modifier: Modifier = Modifier) {
    val menuCount = 13
    val filterCount = 1

    Scaffold(
        topBar = {
            BackButtonTopAppBar(Neutral500)
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.all_menu),
                        style = ourMenuTypography().pretendard_600_20,
                        color = Neutral900
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = String.format(stringResource(R.string.count), menuCount),
                        style = ourMenuTypography().pretendard_500_14,
                        color = Neutral700
                    )
                }

                // TODO: 드롭다운 만들기
                Row {
                    Text(
                        text = stringResource(R.string.sort_type),
                        style = ourMenuTypography().pretendard_500_14,
                        color = Neutral700
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Image(
                        painter = painterResource(id = R.drawable.ic_dropdown_btn),
                        contentDescription = "Expand Down"
                    )
                }
            }

            // TODO: 버튼 누르면 필터 적용
            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.padding(top = 24.dp, bottom = 16.dp, start = 20.dp),
                colors = CardDefaults.cardColors(containerColor = Primary500Main),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_filter),
                        contentDescription = "filter button",
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "$filterCount",
                        color = NeutralWhite,
                        style = ourMenuTypography().pretendard_700_16
                    )
                }
            }

            LazyColumn(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // TODO: 버튼 누르면 해당 페이지로 이동
                items(menuCount) { index ->
                    MenuFolderMenuButton()
                }

                // TODO: 버튼 누르면 메뉴 추가 페이지로 이동
                item {
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp),
                        colors = CardDefaults.cardColors(containerColor = Neutral100),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .height(52.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = stringResource(R.string.add_menu),
                                color = Neutral500,
                                style = ourMenuTypography().pretendard_600_14
                            )
                        }
                    }
                }
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuFolderAllMenuScreenPreview() {
    MenuFolderAllMenuScreen()
}