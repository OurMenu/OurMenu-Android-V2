package com.kuit.ourmenu.ui.searchmenu.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kuit.ourmenu.data.model.map.response.MapDetailResponse
import com.kuit.ourmenu.data.model.map.response.MenuFolderInfo
import com.kuit.ourmenu.ui.common.bottomsheet.MenuInfoBottomSheetContent

@Composable
fun SearchBottomSheetContent(
    modifier: Modifier = Modifier,
    dataList: List<MapDetailResponse>,
    onItemClick: (Long) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(dataList.size) { index ->
            MenuInfoBottomSheetContent(
                modifier = Modifier.padding(vertical = 20.dp),
                menuInfoData = dataList[index],
                onClick = { menuId -> 
                    onItemClick(menuId)
                }
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
            MapDetailResponse(
                menuId = 1,
                menuTitle = "Test Menu",
                storeTitle = "가게 이름",
                menuPrice = 10000,
                menuPinImgUrl = "pin",
                menuTagImgUrls = listOf("한식", "밥"),
                menuImgUrls = listOf(),
                menuFolderInfo = MenuFolderInfo(
                    menuFolderTitle = "Test Store",
                    menuFolderIconImgUrl = "icon",
                    menuFolderCount = 1
                ),
                mapId = 1,
                mapX = 127.0,
                mapY = 37.0
            )
        )
    ){

    }
}