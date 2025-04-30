package com.kuit.ourmenu.ui.my.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.common.topappbar.OurMenuAddButtonTopAppBar
import com.kuit.ourmenu.ui.my.component.MyMealTime
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.NeutralBlack
import com.kuit.ourmenu.ui.theme.OurMenuTypography

@Composable
fun MyScreen() {
    Scaffold(
        topBar = {
            OurMenuAddButtonTopAppBar(
                modifier = Modifier
                    .drawBehind {
                        drawRect(
                            color = NeutralBlack
                        )
                    }
                    .shadow(elevation = 4.dp)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 32.dp, bottom = 24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_profile),
                        contentDescription = null,
                        modifier = Modifier
                            .size(44.dp),
                        tint = Color.Unspecified
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = stringResource(R.string.email),
                        style = OurMenuTypography().pretendard_700_14,
                        color = Neutral900
                    )
                }

                Icon(
                    painter = painterResource(R.drawable.ic_kebab),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(
                            onClick = {
                                // TODO: 모달 만들기
                            }
                        ),
                    tint = Neutral700
                )
            }

            MyMealTime(
                // TODO: mealTimes 데이터 받아오기
            )

            Column(
                modifier = Modifier
                    .padding(top = 32.dp, start = 20.dp, end = 20.dp)
            ) {
                InfoRow(infoTitle = stringResource(R.string.notice)) {
                    // TODO: 공지사항 화면으로 이동
                }
                Spacer(Modifier.height(20.dp))
                InfoRow(infoTitle = stringResource(R.string.customer_service)) {
                    // TODO: 공지사항 화면으로 이동
                }
                Spacer(Modifier.height(20.dp))
                InfoRow(infoTitle = stringResource(R.string.app_review)) {
                    // TODO: 공지사항 화면으로 이동
                }
                Spacer(Modifier.height(20.dp))
                Text(
                    text = stringResource(R.string.app_version, "2.0"),
                    style = OurMenuTypography().pretendard_600_16,
                    color = Neutral900
                )
            }
        }

    }
}

@Composable
fun InfoRow(
    infoTitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = infoTitle,
            style = OurMenuTypography().pretendard_600_16,
            color = Neutral900
        )

        Icon(
            painter = painterResource(R.drawable.ic_arrow_right),
            contentDescription = null,
            modifier = Modifier.height(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyScreenPreview() {
    MyScreen()
}