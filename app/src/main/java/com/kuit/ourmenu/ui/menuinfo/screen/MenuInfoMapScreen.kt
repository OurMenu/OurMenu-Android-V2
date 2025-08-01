package com.kuit.ourmenu.ui.menuinfo.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kuit.ourmenu.data.model.map.response.MapDetailResponse
import com.kuit.ourmenu.data.model.map.response.MenuFolderInfo
import com.kuit.ourmenu.ui.common.GoToMapButton
import com.kuit.ourmenu.ui.common.bottomsheet.BottomSheetDragHandle
import com.kuit.ourmenu.ui.common.bottomsheet.MenuInfoBottomSheetContent
import com.kuit.ourmenu.ui.common.topappbar.OurMenuAddButtonTopAppBar
import com.kuit.ourmenu.ui.theme.NeutralWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuInfoMapScreen(navController: NavController) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    var dragHandleHeight by remember { mutableStateOf(0.dp) }
    var bottomSheetContentHeight by remember { mutableStateOf(0.dp) }

    val density = LocalDensity.current

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContainerColor = NeutralWhite,
        sheetSwipeEnabled = false,
        topBar = { OurMenuAddButtonTopAppBar() },
        sheetDragHandle = {
            BottomSheetDragHandle(
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    dragHandleHeight = density.run { coordinates.size.height.toDp() }
                }
            )
        },
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            MenuInfoBottomSheetContent(
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        val heightPx = coordinates.size.height
                        bottomSheetContentHeight = density.run {
                            heightPx.toDp() + dragHandleHeight
                        }
                    }
                    .padding(bottom = 20.dp),
                // TODO: 이후에 수정 필요
                menuInfoData = MapDetailResponse(
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
        },
        sheetPeekHeight = bottomSheetContentHeight,
    ) { innerPaddings ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings)
        ) {
            // TODO : Map SDK
            GoToMapButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 16.dp, end = 20.dp),
                onClick = { /* TODO : Go To Map Button Click Event */ },
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun MenuInfoMapScreenPreview() {
    val navController = rememberNavController()

    MenuInfoMapScreen(navController)
}