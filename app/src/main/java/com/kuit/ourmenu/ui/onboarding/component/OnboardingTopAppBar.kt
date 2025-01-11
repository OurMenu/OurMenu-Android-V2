package com.kuit.ourmenu.ui.onboarding.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingTopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_top_bar_logo),
                contentDescription = "상단바 로고",
                tint = Color.Unspecified, // 이거 설정 안하면 검정색으로 보임
            )
        },
        navigationIcon = {
            IconButton(onClick = { TODO("뒤로가기 구현") }) {
                Icon(
                    painter = painterResource(R.drawable.ic_top_bar_back),
                    contentDescription = "뒤로가기 버튼",
                )
            }
        },
        modifier =
            Modifier
                .drawBehind {
                    drawRect(
                        color = Color.Black,
                    )
                }.shadow(elevation = 4.dp),
    )
}

@Preview
@Composable
private fun OnboardingTopAppBarPreview() {
    OnboardingTopAppBar()
}
