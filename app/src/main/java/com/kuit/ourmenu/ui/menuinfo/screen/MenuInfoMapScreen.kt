package com.kuit.ourmenu.ui.menuinfo.screen

import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.ui.theme.NeutralWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuInfoMapScreen(modifier: Modifier = Modifier) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val showBottomSheet by remember { mutableStateOf(true) }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = { },
        sheetContainerColor = NeutralWhite,
        sheetPeekHeight = 264.dp,
        sheetSwipeEnabled = false,
        topBar = { },
    ) { }

}

@Preview(showBackground = true)
@Composable
private fun MenuInfoMapScreenPreview() {
    MenuInfoMapScreen()
}