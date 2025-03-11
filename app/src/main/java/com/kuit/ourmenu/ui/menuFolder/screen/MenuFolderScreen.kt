package com.kuit.ourmenu.ui.menuFolder.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.menuFolder.component.MenuFolderButton
import com.kuit.ourmenu.ui.menuFolder.component.MenuFolderTopAppBar
import com.kuit.ourmenu.ui.theme.Neutral100
import com.kuit.ourmenu.ui.theme.Neutral500
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun MenuFolderScreen(modifier: Modifier = Modifier) {
    val menuCount = 5 // 임의로 정한 값
    val menuFolderCount = 8 // 임의로 정한 값

    Scaffold(
        topBar = {
            MenuFolderTopAppBar()
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 상단 메뉴 개수를 표시하는 UI도 함께 스크롤되도록 포함
            item {
                Column(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .height(64.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Primary500Main),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.see_all_menu),
                        color = NeutralWhite,
                        style = ourMenuTypography().pretendard_700_18
                    )

                    Text(
                        text = String.format(stringResource(R.string.menu_count), menuCount),
                        color = NeutralWhite,
                        style = ourMenuTypography().pretendard_500_14,
                    )
                }
            }

            // 여러 개의 MenuFolderButton 추가
            items(menuFolderCount) {
                MenuFolderButton()
            }

            item {
                Card( // 그림자 적용 위해 Card 사용
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.padding(bottom = 20.dp),
                    colors = CardDefaults.cardColors(containerColor = Neutral100),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .height(52.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.add_menu_folder),
                            color = Neutral500,
                            style = ourMenuTypography().pretendard_600_14
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuFolderScreenPreview() {
    MenuFolderScreen()
}