package com.kuit.ourmenu.ui.common.topappbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.NeutralBlack
import com.kuit.ourmenu.ui.theme.Primary500Main
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OurMenuBackButtonTopAppBar(topAppbarText: @Composable (() -> Unit)) {
    TopAppBar(
        title = {
            topAppbarText()
        },
        navigationIcon = {
            IconButton(onClick = { TODO("뒤로가기 구현") }) {
                Icon(
                    painter = painterResource(R.drawable.ic_top_bar_back),
                    contentDescription = "Back"
                )
            }
        },
        modifier = Modifier
            .drawBehind {
                drawRect(
                    color = NeutralBlack
                )
            }
            .shadow(elevation = 4.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun AddMenuTopAppBarPreview() {
    OurMenuBackButtonTopAppBar {
        Text(
            stringResource(R.string.ourmenu),
            style = ourMenuTypography().pretendard_600_18,
            color = Primary500Main
        )
    }
}