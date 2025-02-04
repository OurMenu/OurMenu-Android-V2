package com.kuit.ourmenu.ui.searchmenu.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.ui.common.bottomsheet.MenuInfoBottomSheetContent
import com.kuit.ourmenu.ui.menuinfo.dummy.MenuInfoDummyData

@Composable
fun SearchBottomSheetContent(
    modifier: Modifier = Modifier,
    dataList: List<MenuInfoDummyData>
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(dataList.size) { index ->
            MenuInfoBottomSheetContent(
                modifier = Modifier.padding(vertical = 20.dp)
            )
            if (index != dataList.size - 1) {
                HorizontalDivider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchBottomSheetContentPreview() {
    SearchBottomSheetContent(
        dataList = listOf(
            MenuInfoDummyData.dummyData,
            MenuInfoDummyData.dummyData,
        )
    )
}