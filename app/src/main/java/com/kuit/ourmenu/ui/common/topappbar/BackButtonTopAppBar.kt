package com.kuit.ourmenu.ui.common.topappbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.Neutral500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackButtonTopAppBar(color: Color) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = { TODO("뒤로가기 구현") }) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = color
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun BackButtonTopAppBarPreview() {
    BackButtonTopAppBar(Neutral500)
}