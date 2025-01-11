package com.kuit.ourmenu.ui.addmenu.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMenuTopAppBar(topAppbarText : @Composable (() -> Unit)) {
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
                    color = Color.Black
                )
            }
            .shadow(elevation = 4.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun AddMenuTopAppBarPreview() {
    AddMenuTopAppBar {
        Text(
            "OURMENU",
            color = Color(0xFFFF5420),
            fontWeight = FontWeight.Bold
        )
    }
}