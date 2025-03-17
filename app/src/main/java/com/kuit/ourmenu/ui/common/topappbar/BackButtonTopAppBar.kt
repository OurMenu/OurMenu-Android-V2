package com.kuit.ourmenu.ui.common.topappbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R
import com.kuit.ourmenu.ui.theme.Neutral500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackButtonTopAppBar(color: Color, isKebabVisible: Boolean) {
    TopAppBar(
        title = {
            IconButton(onClick = { TODO("뒤로가기 구현") }) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = color,
                )
            }
        },
        actions = {
            if (isKebabVisible) {
                Icon(
                    painter = painterResource(R.drawable.ic_kebab),
                    modifier = Modifier.padding(end = 18.dp),
                    contentDescription = "Menu",
                    tint = color
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun BackButtonTopAppBarPreview() {
    BackButtonTopAppBar(Neutral500, true)
}