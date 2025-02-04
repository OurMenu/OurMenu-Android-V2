package com.kuit.ourmenu.ui.searchmenu.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuit.ourmenu.ui.common.bottomsheet.MenuInfoBottomSheetContent
import com.kuit.ourmenu.ui.common.chip.MenuFolderChip
import com.kuit.ourmenu.ui.menuinfo.dummy.MenuInfoDummyData
import com.kuit.ourmenu.ui.searchmenu.model.SearchItem
import com.kuit.ourmenu.ui.theme.Neutral700
import com.kuit.ourmenu.ui.theme.Neutral900
import com.kuit.ourmenu.ui.theme.ourMenuTypography
import com.kuit.ourmenu.utils.ExtensionUtil.toWon

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