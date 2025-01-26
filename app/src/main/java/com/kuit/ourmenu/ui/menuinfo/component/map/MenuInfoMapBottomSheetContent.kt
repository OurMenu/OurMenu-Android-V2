package com.kuit.ourmenu.ui.menuinfo.component.map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.ui.menuinfo.dummy.MenuInfoDummyData

@Composable
fun MenuInfoMapBottomSheetContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        MenuInfoMapContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            menuInfoData = MenuInfoDummyData.dummyData
        )

        MenuInfoMapImage(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp),
            menuInfoData = MenuInfoDummyData.dummyData
        )

        MenuInfoMapTagContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            defaultTagList = MenuInfoDummyData.dummyData.defaultTagList,
            customTagList = MenuInfoDummyData.dummyData.customTagList
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuInfoMapBottomSheetContentPreview() {
    MenuInfoMapBottomSheetContent()
}