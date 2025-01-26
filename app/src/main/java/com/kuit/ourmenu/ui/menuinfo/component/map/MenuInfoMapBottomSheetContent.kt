package com.kuit.ourmenu.ui.menuinfo.component.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kuit.ourmenu.ui.menuinfo.dummy.MenuInfoDummyData

@Composable
fun MenuInfoMapBottomSheetContent(modifier: Modifier = Modifier) {
    MenuInfoMapContent(menuInfoData = MenuInfoDummyData.dummyData)

    MenuInfoMapImage(menuInfoData = MenuInfoDummyData.dummyData)
}

@Preview(showBackground = true)
@Composable
private fun MenuInfoMapBottomSheetContentPreview() {
    MenuInfoMapBottomSheetContent()
}