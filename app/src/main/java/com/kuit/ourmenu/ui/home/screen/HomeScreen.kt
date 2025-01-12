package com.kuit.ourmenu.ui.home.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kuit.ourmenu.ui.home.component.HomeTopAppBar

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            HomeTopAppBar()
        }
    ) { innerPadding ->
        Text(
            "Home Screen",
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}